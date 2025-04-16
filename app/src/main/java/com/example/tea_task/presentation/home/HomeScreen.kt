package com.example.tea_task.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.tea_task.presentation.navigation.Screen
import com.example.tea_task.ui.theme.Black
import com.example.tea_task.ui.theme.LARGE_MARGIN
import com.example.tea_task.util.ErrorUi
import com.example.tea_task.util.LoadingIndicator
import com.example.tea_task.util.RequestState

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState.collectAsState().value
    val cachedData = uiState.cachedCompetitions.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(top = LARGE_MARGIN)
    ) {
        when (uiState.competitionsState) {
            RequestState.LOADING -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            RequestState.SUCCESS -> {
                val competitions =
                    if (cachedData.isNotEmpty()) cachedData
                    else uiState.competitionsData.competitions

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Black),
                    contentPadding = PaddingValues(bottom = LARGE_MARGIN)
                ) {
                    items(competitions) { competition ->
                        ListItemHome(
                            currentItem = competition,
                            onItemClicked = { competitionItem ->
                                viewModel.onIntent(HomeIntent.OnCompetitionClicked(competitionItem))
                                navController.navigate(Screen.DETAILS_SCREEN.route)
                            }
                        )
                    }
                }
            }

            RequestState.ERROR -> {
                // No internet connection and cached data are available
                if (uiState.isNetworkError && cachedData.isNotEmpty()) {
                    viewModel.onIntent(HomeIntent.LoadOfflineContent)
                } else {
                    // No internet connection and cached data are not available
                    ErrorUi(
                        onRetryClicked = {
                            viewModel.onIntent(HomeIntent.RetryApi)
                        },
                        isNetworkError = uiState.isNetworkError,
                        modifier = Modifier
                    )
                }
            }

            else -> Unit
        }
    }
}
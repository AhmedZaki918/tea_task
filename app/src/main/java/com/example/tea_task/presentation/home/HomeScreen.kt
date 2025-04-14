package com.example.tea_task.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tea_task.ui.theme.Black
import com.example.tea_task.util.LoadingIndicator
import com.example.tea_task.util.RequestState
import com.example.tea_task.util.toast

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Black)) {

        when (uiState.competitionsState) {
            RequestState.LOADING -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            RequestState.SUCCESS -> {
                context.toast(
                    "SUCCESS ${uiState.competitionsData.competitions[0].area.name}",
                    Toast.LENGTH_LONG
                )
            }

            RequestState.ERROR -> {
                val isNetworkError = uiState.isNetworkError

                if (isNetworkError){
                    context.toast("connection lost", Toast.LENGTH_LONG)
                    // Show connection lost ui
                } else {
                    context.toast("Ops, something went wrong", Toast.LENGTH_LONG)
                }
            }
        }
    }
}
package com.example.tea_task.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tea_task.presentation.details.DetailsScreen
import com.example.tea_task.presentation.home.HomeScreen
import com.example.tea_task.presentation.home.HomeViewModel

@Composable
fun NavGraph(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.HOME_SCREEN.route
    ) {

        composable(route = Screen.HOME_SCREEN.route) {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(route = Screen.DETAILS_SCREEN.route) {
            DetailsScreen(
                viewModel = viewModel
            )
        }
    }
}
package com.example.tea_task.presentation.home

sealed class HomeIntent {
    object RetryApi : HomeIntent()
}
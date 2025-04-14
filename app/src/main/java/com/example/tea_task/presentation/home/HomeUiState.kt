package com.example.tea_task.presentation.home

import com.example.tea_task.data.model.competition.CompetitionsResponse
import com.example.tea_task.util.RequestState

data class HomeUiState (
    val competitionsData : CompetitionsResponse = CompetitionsResponse(),
    val competitionsState : RequestState = RequestState.IDLE,
    val isNetworkError : Boolean = false
)

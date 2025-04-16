package com.example.tea_task.presentation.home

import com.example.tea_task.data.model.competition.Competition
import com.example.tea_task.data.model.competition.CompetitionsResponse
import com.example.tea_task.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class HomeUiState(
    val competitionsData: CompetitionsResponse = CompetitionsResponse(),
    val competitionsState: RequestState = RequestState.IDLE,
    val isNetworkError: Boolean = false,
    val competitionDetails: Competition = Competition(),
    val cachedCompetitions: StateFlow<List<Competition>> = MutableStateFlow(emptyList())
)

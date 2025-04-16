package com.example.tea_task.presentation.home

import com.example.tea_task.data.model.competition.Competition

sealed class HomeIntent {
    object RetryApi : HomeIntent()
    data class OnCompetitionClicked(var competition: Competition) : HomeIntent()
    object LoadOfflineContent : HomeIntent()
}
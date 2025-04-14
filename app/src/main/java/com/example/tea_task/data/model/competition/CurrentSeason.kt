package com.example.tea_task.data.model.competition

import com.example.tea_task.data.model.competition.Winner

data class CurrentSeason(
    val currentMatchday: Int = 0,
    val endDate: String = "",
    val id: Int = 0,
    val startDate: String = "",
    val winner: Winner = Winner()
)
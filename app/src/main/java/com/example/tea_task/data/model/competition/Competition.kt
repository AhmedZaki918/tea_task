package com.example.tea_task.data.model.competition

import com.example.tea_task.data.model.competition.CurrentSeason

data class Competition(
    val area: Area = Area(),
    val code: String = "",
    val currentSeason: CurrentSeason = CurrentSeason(),
    val emblem: String = "",
    val id: Int = 0,
    val lastUpdated: String = "",
    val name: String = "",
    val numberOfAvailableSeasons: Int = 0,
    val plan: String = "",
    val type: String = ""
)
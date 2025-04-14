package com.example.tea_task.data.model.competition

data class CompetitionsResponse(
    val competitions: List<Competition> = listOf(),
    val count: Int = 0
)
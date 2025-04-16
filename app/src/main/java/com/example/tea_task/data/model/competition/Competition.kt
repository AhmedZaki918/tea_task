package com.example.tea_task.data.model.competition

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tea_task.data.local.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Competition(
    @PrimaryKey
    val id: Int = 0,
    val area: Area = Area(),
    val code: String = "",
    val currentSeason: CurrentSeason = CurrentSeason(),
    val emblem: String = "",
    val lastUpdated: String = "",
    val name: String = "",
    val numberOfAvailableSeasons: Int = 0,
    val plan: String = "",
    val type: String = ""
)
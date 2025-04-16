package com.example.tea_task.data.local

import androidx.room.TypeConverter
import com.example.tea_task.data.model.competition.Area
import com.example.tea_task.data.model.competition.CurrentSeason
import com.google.gson.Gson

class Converter {

    // Area class
    @TypeConverter
    fun fromArea(area: Area): String {
        return Gson().toJson(area)
    }

    @TypeConverter
    fun toArea(name: String): Area {
        return Gson().fromJson(name, Area::class.java)
    }

    // CurrentSeason class
    @TypeConverter
    fun fromCurrentSeason(season: CurrentSeason): String {
        return Gson().toJson(season)
    }

    @TypeConverter
    fun toCurrentSeason(name: String): CurrentSeason {
        return Gson().fromJson(name, CurrentSeason::class.java)
    }
}
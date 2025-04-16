package com.example.tea_task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tea_task.data.model.competition.Competition

@Database(
    entities = [Competition::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converter::class)
abstract class CompetitionDatabase : RoomDatabase() {
    abstract fun getCompetitionDao(): CompetitionDao
}
package com.example.tea_task.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tea_task.data.local.Constants.TABLE_NAME
import com.example.tea_task.data.model.competition.Competition
import kotlinx.coroutines.flow.Flow

@Dao
interface CompetitionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompetition(competition: Competition)

    @Query("SELECT * FROM $TABLE_NAME")
     fun getAllCompetition(): Flow<List<Competition>>
}
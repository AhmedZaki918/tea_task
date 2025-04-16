package com.example.tea_task.data.repository

import com.example.tea_task.data.local.CompetitionDao
import com.example.tea_task.data.model.competition.Competition
import com.example.tea_task.data.remote.APIFootball
import com.example.tea_task.data.remote.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val api: APIFootball,
    val dao: CompetitionDao
) : SafeApiCall {

    suspend fun competitions() = safeApiCall {
        api.fetchCompetitions()
    }

    // Caching data and showing it when no internet access
    suspend fun saveCompetitions(competitions: List<Competition>) {
        for (item in competitions) {
            dao.addCompetition(item)
        }
    }

    // Retrieving the data from the cache
    fun getCompetitions(): Flow<List<Competition>> {
        return dao.getAllCompetition()
    }
}
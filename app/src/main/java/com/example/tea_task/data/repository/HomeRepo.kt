package com.example.tea_task.data.repository

import com.example.tea_task.data.remote.APIFootball
import com.example.tea_task.data.remote.SafeApiCall
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val api: APIFootball
) : SafeApiCall {

    suspend fun competitions() = safeApiCall {
        api.fetchCompetitions()
    }

    // Caching data and showing it when no internet access
//    suspend fun saveMatchesList(matches: List<MatchesItem>){
//        for (match in matches){
//            dao.addMatch(match)
//        }
//    }
}
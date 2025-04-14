package com.example.tea_task.data.remote

import com.example.tea_task.data.local.Constants.API_KEY
import com.example.tea_task.data.model.competition.CompetitionsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface APIFootball {

    @GET("competitions/")
    suspend fun fetchCompetitions(
        @Header("X-Auth-Token") token: String = API_KEY
    ): CompetitionsResponse
}
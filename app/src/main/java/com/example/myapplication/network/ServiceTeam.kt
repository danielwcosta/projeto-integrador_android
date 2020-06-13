package com.example.myapplication.network

import com.example.myapplication.model.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceTeam {

    @GET("lookup_all_teams.php")
    suspend fun getServiceLeague(
            @Query("id") id : Int
    ):TeamResponse
}
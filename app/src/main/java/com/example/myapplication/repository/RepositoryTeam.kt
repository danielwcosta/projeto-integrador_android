package com.example.myapplication.repository

import com.example.myapplication.model.TeamResponse
import com.example.myapplication.network.RetroInit
import com.example.myapplication.network.ServiceTeam

class RepositoryTeam {
        private val url = "https://www.thesportsdb.com/api/v1/json/1/"

        private val service = ServiceTeam::class

        private val initService = RetroInit(url).create(service)

        suspend fun getLeague(id : Int) : TeamResponse = initService.getServiceLeague(id)

}
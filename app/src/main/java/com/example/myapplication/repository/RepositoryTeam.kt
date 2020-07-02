package com.example.myapplication.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.myapplication.data.DatabaseBuilder
import com.example.myapplication.model.Team
import com.example.myapplication.model.TeamResponse
import com.example.myapplication.network.RetroInit
import com.example.myapplication.network.ServiceTeam

class RepositoryTeam {
        private val url = "https://www.thesportsdb.com/api/v1/json/1/"

        private val service = ServiceTeam::class

        private val initService = RetroInit(url).create(service)

        suspend fun getLeague(id : Int) : TeamResponse = initService.getServiceLeague(id)

        private fun initDataBase(context : Context) = DatabaseBuilder.getAppDatabase(context)

        suspend fun pegarTodaListaTeams(context: Context): MutableList<Team> = initDataBase(context).accessTeam().puxaTodoTeam()

        suspend fun inserirTeamsNaLista(context: Context, team: Team): Unit= initDataBase(context).accessTeam().inserirTeam(team)

        fun pegaTeamPorId(context: Context, id: Int): Team = initDataBase(context).accessTeam().encontrarTeamId(id)

        suspend fun removerTeam(context: Context, team: Team): Unit= initDataBase(context).accessTeam().deletarTeam(team)

}
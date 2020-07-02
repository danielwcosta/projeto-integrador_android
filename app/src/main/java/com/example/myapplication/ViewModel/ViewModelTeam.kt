package com.example.myapplication.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Team
import com.example.myapplication.repository.RepositoryTeam
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelTeam : ViewModel() {

    val listTeam = MutableLiveData<ArrayList<Team>>()
    val listFavoritos = MutableLiveData<MutableList<Team>>()
    val repository = RepositoryTeam()

    fun getTeamsLeague(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        repository.getLeague(id).let { TeamResponse ->
            listTeam.postValue(TeamResponse.teams)
        }
    }

    fun getTimesFavoritos(context: Context) = CoroutineScope(Dispatchers.IO).launch {
        repository.pegarTodaListaTeams(context).let { Team ->
            listFavoritos.postValue(Team)
        }
    }

    fun putTimesFavorito(context: Context, team: Team) = CoroutineScope(Dispatchers.IO).launch {
        repository.inserirTeamsNaLista(context,team)
    }

    fun searchTimeById(context: Context, id: Int):Team = repository.pegaTeamPorId(context,id)

    fun searchTimeByName(context: Context, strTeam: String):Boolean = repository.pegaTeamPorNome(context,strTeam)

    fun removeTimesFavorito(context: Context, team: Team) = CoroutineScope(Dispatchers.IO).launch {
        repository.removerTeam(context,team)
    }

}
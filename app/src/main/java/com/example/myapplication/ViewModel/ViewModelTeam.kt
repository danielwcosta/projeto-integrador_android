package com.example.myapplication.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Team
import com.example.myapplication.repository.RepositoryTeam
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelTeam : ViewModel() {

    val listTeam    = MutableLiveData<ArrayList<Team>>()
    val repository  = RepositoryTeam()
    
    fun getTeamsLeague(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        repository.getLeague(id).let{ TeamResponse->
            listTeam.postValue(TeamResponse.teams)

        }
    }

 }
package com.example.myapplication.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Team
import com.example.myapplication.model.Usuario
import com.example.myapplication.repository.RepositoryUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelUsuarioRoom: ViewModel() {

    val listUsuario = MutableLiveData<MutableList<Usuario>>()
    val repository = RepositoryUsuario()

    fun getListaUsuario(context: Context) = CoroutineScope(Dispatchers.IO).launch {
        repository.puxaListaUsuario(context).let { Team ->
            listUsuario.postValue(Team)
        }
    }

    fun addUsuario(context: Context, usuario: Usuario) = CoroutineScope(Dispatchers.IO).launch {
        repository.inserirUsuario(context,usuario)
    }

    fun searchUsuarioByEmail(context: Context, email: String):Usuario = repository.pegaUsuarioPorEmail(context,email)

    fun searchUsuarioByUid(context: Context, uid: String):Usuario = repository.pegaUsuarioPorUid(context,uid)

    fun updateUsuario(context: Context, usuario: Usuario) = CoroutineScope(Dispatchers.IO).launch {
        repository.atulizarUsuario(context,usuario)
    }

}
package com.example.myapplication.repository

import android.content.Context
import com.example.myapplication.data.DatabaseBuilder
import com.example.myapplication.model.Usuario

class RepositoryUsuario {

    private fun initDataBase(context : Context) = DatabaseBuilder.getAppDatabase(context)

    suspend fun puxaListaUsuario(context: Context): MutableList<Usuario> = initDataBase(context).accessUsuario().puxaTodaLista()

    suspend fun inserirUsuario(context: Context, usuario: Usuario): Unit= initDataBase(context).accessUsuario().inserir(usuario)

    fun pegaUsuarioPorUid(context: Context, uid: String): Usuario = initDataBase(context).accessUsuario().procuraUserId(uid)

    fun atulizarUsuario(context: Context, usuario: Usuario): Unit = initDataBase(context).accessUsuario().atualizar(usuario)
}
package com.example.myapplication.repository

import android.content.Context
import com.example.myapplication.data.DatabaseBuilder
import com.example.myapplication.model.Usuario

class RepositoryUsuario {

    private fun initDataBase(context : Context) = DatabaseBuilder.getAppDatabase(context)

    suspend fun puxaListaUsuario(context: Context): MutableList<Usuario> = initDataBase(context).accessUsuario().puxaTodaLista()

    suspend fun inserirUsuario(context: Context, usuario: Usuario): Unit= initDataBase(context).accessUsuario().inserir(usuario)

    fun pegaUsuarioId(context: Context, id: Int): Usuario = initDataBase(context).accessUsuario().encontrarPorId(id)

    fun pegaUsuarioPorUid(context: Context, uid: String): Usuario = initDataBase(context).accessUsuario().procuraUserId(uid)

    fun pegaUsuarioPorEmail(context: Context, email: String): Usuario = initDataBase(context).accessUsuario().procuraUserEmail(email)

    fun atulizarUsuario(context: Context, usuario: Usuario): Unit = initDataBase(context).accessUsuario().atualizar(usuario)
}
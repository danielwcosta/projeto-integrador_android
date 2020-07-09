package com.example.myapplication.data

import androidx.room.*
import com.example.myapplication.model.Usuario

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuario")
    fun puxaTodaLista(): MutableList<Usuario>

    @Query("SELECT * FROM usuario where id LIKE  :id")
    fun encontrarPorId(id: Int): Usuario

    @Query("SELECT * FROM usuario where uid LIKE  :uid")
    fun procuraUserId(uid: String): Usuario

    @Query("SELECT * FROM usuario where email LIKE  :email")
    fun procuraUserEmail(email: String): Usuario

    @Query("SELECT COUNT(*) from usuario")
    fun contarTodos(): Int

    @Insert
    fun inserir(vararg usuarios: Usuario)

    @Delete
    fun deletar(usuario: Usuario)

    @Update
    fun atualizar(usuario: Usuario)


}
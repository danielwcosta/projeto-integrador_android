package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM team")
    suspend fun puxaTodoTeam(): MutableList<Team>

    @Query("SELECT * FROM team")
    fun puxaAe(): List<Team>

    @Query("SELECT * from team")
    fun todosTeamFavoritos() : LiveData<List<Team>>

    @Query("SELECT * FROM team where id LIKE  :id")
    fun encontrarTeamId(id: Int): Team

    @Query("SELECT * FROM team where strTeam LIKE  :strTeam")
    fun encontrarTeamName(strTeam: String): Boolean

    @Query("SELECT COUNT(*) from team")
    fun contarTodosTeam(): Int

    @Insert
    suspend fun inserirTeam(team: Team)

    @Delete
    fun deletarTeam(team: Team)

    @Update
    fun atualizarTeam(team: Team)

}
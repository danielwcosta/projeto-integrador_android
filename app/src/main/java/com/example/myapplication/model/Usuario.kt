package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "name_complete")
    var nomeCompleto: String,

    @ColumnInfo(name = "user")
    var user: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "senha")
    var senha: String,

    @ColumnInfo(name = "foto")
    var foto: String = "",

    @ColumnInfo(name = "acertos")
    var acertos: Int = 0,

    @ColumnInfo(name = "erros")
    var erros: Int = 0,

    @ColumnInfo(name = "media")
    var media: Double = 0.0,

    @ColumnInfo(name = "times_favoritos")
    var times_favoritos: String = ""

)
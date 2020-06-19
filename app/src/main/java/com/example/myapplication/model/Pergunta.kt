package com.example.myapplication.model


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import org.jetbrains.annotations.Nullable

@Parcelize
data class Pergunta(
        val id: Int,
        val id_tipo: Int,
        val tipo: String,
        val imagem: String?,
        val pergunta: String,
        val alternativa1: String,
        val alternativa2: String,
        val alternativa3: String,
        val alternativa4: String,
        val resposta: String
) : Parcelable
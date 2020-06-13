package com.example.myapplication.model


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class PerguntasResponse(
    val perguntas: List<Pergunta>
) : Parcelable
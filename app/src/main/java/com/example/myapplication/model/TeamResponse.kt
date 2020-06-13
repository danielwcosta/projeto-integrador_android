package com.example.myapplication.model


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class TeamResponse(
    val teams: ArrayList<Team>
) : Parcelable
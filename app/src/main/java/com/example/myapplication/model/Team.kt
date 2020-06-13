package com.example.myapplication.model


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Team(
    val strTeam: String?,
    val strCountry: String?,
    val intStadiumCapacity: String?,
    val strStadiumLocation: String?,
    val strStadium: String?,
    val strStadiumThumb: String?,
    val strTeamBadge: String?,
    val strTeamJersey: String?,
    val strLeague: String?,
    val intFormedYear: String?,
    val strAlternate: String?,
    val strRSS: String?,
    val strWebsite: String?,
    val strFacebook: String?,
    val strTwitter: String?,
    val strInstagram: String?,
    val strDescriptionPT: String?,
    val strYoutube: String?

) : Parcelable
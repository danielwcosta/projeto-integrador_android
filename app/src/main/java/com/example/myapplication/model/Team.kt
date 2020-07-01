package com.example.myapplication.model


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "team")
data class Team(
        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "strTeam")
        val strTeam: String?,

        @ColumnInfo(name = "strCountry")
        val strCountry: String?,

        @ColumnInfo(name = "intStadiumCapacity")
        val intStadiumCapacity: String?,

        @ColumnInfo(name = "strStadiumLocation")
        val strStadiumLocation: String?,

        @ColumnInfo(name = "strStadium")
        val strStadium: String?,

        @ColumnInfo(name = "strStadiumThumb")
        val strStadiumThumb: String?,

        @ColumnInfo(name = "strTeamBadge")
        val strTeamBadge: String?,

        @ColumnInfo(name = "strTeamJersey")
        val strTeamJersey: String?,

        @ColumnInfo(name = "strLeague")
        val strLeague: String?,

        @ColumnInfo(name = "intFormedYear")
        val intFormedYear: String?,

        @ColumnInfo(name = "strAlternate")
        val strAlternate: String?,

        @ColumnInfo(name = "strRSS")
        val strRSS: String?,

        @ColumnInfo(name = "strWebsite")
        val strWebsite: String?,

        @ColumnInfo(name = "strFacebook")
        val strFacebook: String?,

        @ColumnInfo(name = "strTwitter")
        val strTwitter: String?,

        @ColumnInfo(name = "strInstagram")
        val strInstagram: String?,

        @ColumnInfo(name = "strDescriptionPT")
        val strDescriptionPT: String?,

        @ColumnInfo(name = "strYoutube")
        val strYoutube: String?

) : Parcelable
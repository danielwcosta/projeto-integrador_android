package com.example.myapplication.data

import androidx.room.TypeConverter
import com.example.myapplication.model.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class UsuarioTypeConverter {
    @TypeConverter
    fun fromListTeam(value: String?): List<Team?>? {
        val listType = object : TypeToken<List<Team?>?>() {}.type as Type
        return Gson().fromJson<List<Team?>>(value, listType)
    }

    @TypeConverter
    fun fromListTeamObject(list: List<Team?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}
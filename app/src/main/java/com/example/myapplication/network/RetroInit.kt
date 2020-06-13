package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

private val gsonConverter: GsonConverterFactory = GsonConverterFactory.create()

class RetroInit(url: String) {

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(gsonConverter)
            .build()

    fun <T : Any> create(clazz: KClass<T>): T = retrofit.create(clazz.java)
}
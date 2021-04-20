package com.example.designpatterns.patterns.creational.singleton

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Exemplo de um HttpClient sendo criado como singleton
object HttpClient {
    fun getInstance(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.com")
            .client(OkHttpClient())
            .build()
            .create(Api::class.java)
    }
}

interface Api
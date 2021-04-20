package com.example.handsonweek3.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET("anime/{id}")
    fun getDetails(@Path(value = "id") id: Int): Call<AnimeResult>

    @GET("anime/{id}/characters_staff")
    fun getCharactersAndStaff(@Path(value = "id") id: Int): Call<AnimeCharactersStaffResult>
}
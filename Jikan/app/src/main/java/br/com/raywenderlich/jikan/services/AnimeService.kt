package br.com.raywenderlich.jikan.services

import br.com.raywenderlich.jikan.models.Anime
import br.com.raywenderlich.jikan.models.CharacterResult
import br.com.raywenderlich.jikan.models.Staff
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {
    @GET("anime/{id}")
    fun getDetails(
        @Path("id") id: Int
    ): Call<Anime>

    @GET("anime/{id}/characters_staff")
    fun getCharacterAndStaff(
        @Path("id") id: Int
    ): Call<Staff>
}
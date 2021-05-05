package br.com.handson5.repository

import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("movies/")
    fun getMovies(): Call<MovieResponse>
}

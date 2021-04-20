package com.example.retrofitbootcamp.services.jikan

import com.example.retrofitbootcamp.handlers.RequestResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/anime")
    fun search(@Query(value = "q") search: String): Call<SearchResult>
}
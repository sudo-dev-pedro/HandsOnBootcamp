package com.example.retrofitbootcamp.services.jikan

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AnimeResult (
    @Json(name = "title")
    val title: String = "",
    @Json(name = "image_url")
    val imageUrl: String = "",
    @Json(name = "synopsis")
    val synopsis: String = ""
)
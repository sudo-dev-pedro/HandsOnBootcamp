package com.example.handsonweek3.services

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.Instant


@JsonClass(generateAdapter = true)
data class AnimeResult (
    @Json(name = "mal_id")
    val id: Int = 0,

    @Json(name = "title")
    val title: String = "",

    @Json(name = "image_url")
    val imageUrl: String = "",

    @Json(name = "synopsis")
    val synopsis: String = "",

    @Json(name = "episodes")
    val episodes: Int = 0,

    @Json(name = "score")
    val score: Float = 0f,

    @Json(name = "airing")
    val airing: Boolean = false
)
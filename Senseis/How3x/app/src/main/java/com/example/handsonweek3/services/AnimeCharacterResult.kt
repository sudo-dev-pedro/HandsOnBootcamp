package com.example.handsonweek3.services

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.Instant


@JsonClass(generateAdapter = true)
data class AnimeCharacterResult (
    @Json(name = "name")
    val name: String = "",

    @Json(name = "image_url")
    val imageUrl: String = "",

    @Json(name = "role")
    val role: String = ""
)
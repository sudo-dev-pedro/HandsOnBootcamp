package com.example.handsonweek3.services

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AnimeCharactersStaffResult (
    @Json(name = "characters")
    val characters: List<AnimeCharacterResult> = emptyList()
)
package br.com.raywenderlich.jikan.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Staff(
    @Json(name = "characters")
    val staff: List<CharacterResult> = emptyList()
)

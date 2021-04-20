package br.com.raywenderlich.jikan.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SearchResult(
    @Json(name = "results")
    val results: List<Anime> = emptyList()
)
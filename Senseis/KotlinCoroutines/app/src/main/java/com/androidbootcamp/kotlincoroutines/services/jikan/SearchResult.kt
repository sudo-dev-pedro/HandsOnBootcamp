package com.androidbootcamp.kotlincoroutines.services.jikan

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SearchResult (
    @Json(name = "results")
    val results: List<AnimeResult> = emptyList(),

    @Json(name = "last_page")
    val totalPages: Int = 0
)
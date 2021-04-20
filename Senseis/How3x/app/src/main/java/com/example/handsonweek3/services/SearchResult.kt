package com.example.handsonweek3.services

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SearchResult (
    @Json(name = "results")
    val results: List<SearchAnimeResult> = emptyList(),

    @Json(name = "last_page")
    val totalPages: Int = 0
)
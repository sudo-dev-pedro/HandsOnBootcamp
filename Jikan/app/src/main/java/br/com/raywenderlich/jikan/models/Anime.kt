package br.com.raywenderlich.jikan.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Anime(
    @Json(name = "mal_id")
    val id: Int = 0,

    @Json(name = "title")
    val title: String = "",

    @Json(name = "image_url")
    val imageUrl: String = "",

    @Json(name = "synopsis")
    val synopsis: String = "",

    @Json(name = "episodes")
    val episode: Int = 0,

    @Json(name = "score")
    val score: String = "",

    @Json(name = "airing")
    val airing: Boolean = false
) : Serializable


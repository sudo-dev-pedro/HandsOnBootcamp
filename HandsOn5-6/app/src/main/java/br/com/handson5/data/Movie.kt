package br.com.handson5.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "description")
    val description: String = "",
    @Json(name = "image")
    val image: String = "",
    @Json(name = "title")
    val title: String = ""
) : Serializable
package br.com.raywenderlich.jikan.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class CharacterResult(
    @Json(name = "name")
    val name: String = "",

    @Json(name= "image_url")
    val imgUrl :String = "",

    @Json(name ="role")
    val role:String = ""
): Serializable

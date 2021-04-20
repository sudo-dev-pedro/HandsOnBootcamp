package br.com.handson5.repository

import br.com.handson5.data.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieResponse {
    @Json(name = "movies")
    var movies: List<Movie>? = null
}
package br.com.handson5.repository

import br.com.handson5.data.Movie

interface MovieDataSource {

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies: List<Movie>)
        fun onDataNotAvailable()
        fun onError()
    }

    suspend fun getMovies(callback: LoadMoviesCallback)
}
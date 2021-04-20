package br.com.handson5.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieRepository : MovieDataSource, KoinComponent {

    private val movieApi: MovieApi by inject()

    override suspend fun getMovies(callback: MovieDataSource.LoadMoviesCallback) =
        withContext(Dispatchers.IO) {

            val response = movieApi.getMovies().execute()

            if (response.isSuccessful) {
                response.body()?.movies?.let {
                    callback.onMoviesLoaded(it)
                } ?: run {
                    callback.onDataNotAvailable()
                }
            } else {
                callback.onDataNotAvailable()
            }
        }

    companion object {
        var URL = "https://demo2458961.mockable.io"
    }
}
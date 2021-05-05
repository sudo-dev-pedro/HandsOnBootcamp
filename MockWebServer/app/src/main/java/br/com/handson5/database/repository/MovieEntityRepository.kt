package br.com.handson5.database.repository

import br.com.handson5.data.Movie
import br.com.handson5.database.dao.MovieDao

class MovieEntityRepository(
    private val movieDao: MovieDao
) {
    suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    suspend fun getMovies(): List<Movie> {
        return movieDao.getMovies()
    }

    suspend fun getMovie(id: Long): Movie {
        return movieDao.getMovie(id)
    }

    suspend fun getFavoriteMovies(): List<Movie> {
        return movieDao.getFavoriteMovies()
    }

    suspend fun updateFavorite(id: Long, value: Int) {
        movieDao.updateFavorite(id, value)
    }

    suspend fun deleteMovie(id: Long) {
        movieDao.deleteMovieById(id)
    }
}
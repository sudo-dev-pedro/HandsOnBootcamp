package br.com.handson5.database.repository

import br.com.handson5.database.dao.MovieDao
import br.com.handson5.database.entity.MovieEntity

class MovieEntityRepository(
    private val movieDao: MovieDao
) {
    suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun getMovies() {
        movieDao.getMovies()
    }

    suspend fun deleteMovie(id: Long) {
        movieDao.deleteMovieById(id)
    }
}
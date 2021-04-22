package br.com.handson5.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.handson5.database.entity.MovieEntity
import br.com.handson5.database.queries.MoviesQueries.SELECT_ALL

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movie: MovieEntity)

    @Query(SELECT_ALL)
    suspend fun getMovies()

    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteMovieById(id: Long)
}
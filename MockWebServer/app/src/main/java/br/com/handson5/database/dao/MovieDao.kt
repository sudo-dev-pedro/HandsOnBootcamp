package br.com.handson5.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.handson5.data.Movie
import br.com.handson5.database.DatabaseConstants.MOVIE_TABLE_NAME
import br.com.handson5.database.queries.MoviesQueries.SELECT_ALL
import br.com.handson5.database.queries.MoviesQueries.SELECT_FAVORITES

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: Movie)

    @Query(SELECT_ALL)
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM $MOVIE_TABLE_NAME WHERE id = :id")
    suspend fun getMovie(id: Long): Movie

    @Query(SELECT_FAVORITES)
    suspend fun getFavoriteMovies(): List<Movie>

    @Query("UPDATE $MOVIE_TABLE_NAME SET favorite = :value WHERE id = :id")
    suspend fun updateFavorite(id: Long, value: Int)

    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteMovieById(id: Long)
}
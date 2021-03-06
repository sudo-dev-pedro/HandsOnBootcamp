package br.com.handson5.database.queries

import br.com.handson5.database.DatabaseConstants.MOVIE_TABLE_NAME

object MoviesQueries {

    const val SELECT_ALL = "SELECT * FROM $MOVIE_TABLE_NAME"
    const val SELECT_FAVORITES = "SELECT * FROM $MOVIE_TABLE_NAME WHERE favorite = 1"
}
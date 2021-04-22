package br.com.handson5.database.repository

import br.com.handson5.database.dao.MovieDao

class MovieEntityRepository(
    private val movieDao: MovieDao
)
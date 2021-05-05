package br.com.handson5.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.handson5.data.Movie
import br.com.handson5.database.dao.MovieDao
import br.com.handson5.database.dao.UserDao
import br.com.handson5.database.entity.User

@Database(
    entities = [(Movie::class), (User::class)],
    version = 1,
    exportSchema = false
)

abstract class DatabaseManager : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun userDao(): UserDao
}
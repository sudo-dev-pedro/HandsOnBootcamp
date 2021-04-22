package br.com.handson5.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.handson5.database.dao.MovieDao
import br.com.handson5.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
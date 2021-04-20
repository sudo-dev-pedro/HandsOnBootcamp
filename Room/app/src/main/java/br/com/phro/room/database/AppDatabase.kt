package br.com.phro.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.phro.room.database.dao.UserDAO
import br.com.phro.room.database.entity.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
}
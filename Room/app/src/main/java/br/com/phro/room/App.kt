package br.com.phro.room

import android.app.Application
import androidx.room.Room
import br.com.phro.room.database.AppDatabase
import br.com.phro.sqlite.database.DatabaseConstants.DATABASE_NAME

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AppDatabase::
            class.java,
            DATABASE_NAME
        ).build()
    }
}
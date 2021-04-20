package br.com.raywenderlich.flighter.app

import android.app.Application
import androidx.room.Room
import br.com.raywenderlich.flighter.database.AppDatabase
import br.com.raywenderlich.flighter.database.DatabaseConstants.DATABASE_NAME

class FlighterApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}
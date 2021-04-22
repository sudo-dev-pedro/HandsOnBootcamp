package br.com.handson5

import android.app.Application
import androidx.room.Room
import br.com.handson5.database.AppDatabase
import br.com.handson5.database.DatabaseConstants.DATABASE_NAME
import br.com.handson5.di.appModule
import br.com.handson5.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    repositoryModule
                )
            )
        }

        database = Room.databaseBuilder(
            this,
            AppDatabase::
            class.java,
            DATABASE_NAME
        ).build()
    }
}
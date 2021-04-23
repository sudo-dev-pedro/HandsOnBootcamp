package br.com.handson5

import android.app.Application
import androidx.room.Room
import br.com.handson5.database.DatabaseManager
import br.com.handson5.database.DatabaseConstants.DATABASE_NAME
import br.com.handson5.di.appModule
import br.com.handson5.di.repositoryModule
import br.com.handson5.di.viewModelsModule
import net.sqlcipher.database.SQLiteDatabase.getBytes
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        const val USER_HASH_CODE = "USER_HASH_CODE"
        lateinit var databaseManager: DatabaseManager
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    viewModelsModule
                )
            )
        }

        val builder = Room.databaseBuilder(
            this,
            DatabaseManager::class.java,
            DATABASE_NAME
        )

        val factory = SupportFactory(getBytes("PassPhrase".toCharArray()))
        builder.openHelperFactory(factory)
        databaseManager = builder.build()

    }
}
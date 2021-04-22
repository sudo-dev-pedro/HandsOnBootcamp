package br.com.raywenderlich.jikan

import android.app.Application
import br.com.raywenderlich.jikan.adapters.adapterModule
import br.com.raywenderlich.jikan.services.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    apiModule,
                    adapterModule
                )
            )
        }
    }
}
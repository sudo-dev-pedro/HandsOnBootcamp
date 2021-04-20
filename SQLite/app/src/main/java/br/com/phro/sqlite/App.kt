package br.com.phro.sqlite

import android.app.Application
import br.com.phro.sqlite.database.DatabaseHelper

class App : Application() {

    companion object {
        private lateinit var instance: App
        val database by lazy {
            DatabaseHelper(instance)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
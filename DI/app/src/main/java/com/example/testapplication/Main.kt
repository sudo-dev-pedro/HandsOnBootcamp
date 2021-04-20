package com.example.testapplication

import android.app.Application
import com.example.testapplication.di.appModule
import org.koin.core.context.startKoin

class Main : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { appModule }
    }
}


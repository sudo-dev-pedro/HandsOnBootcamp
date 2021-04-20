package com.example.how2x

import android.app.Application
import android.content.SharedPreferences

class App: Application() {

    companion object {
        var sharedPreferences: SharedPreferences? = null
    }
}
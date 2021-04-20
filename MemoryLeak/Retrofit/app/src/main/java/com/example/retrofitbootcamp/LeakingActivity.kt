package com.example.retrofitbootcamp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class LeakingActivity : AppCompatActivity() {

    private val listener = Listener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaking)
    }

    override fun onStart() {
        super.onStart()
        GlobalSingleton.register(listener)
    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalSingleton.unregister(listener)
    }

    private inner class Listener : GlobalSingletonListener {
        override fun onEvent() {
            Log.d(this@LeakingActivity.toString(), "Hi -> I am alive")
        }
    }
}
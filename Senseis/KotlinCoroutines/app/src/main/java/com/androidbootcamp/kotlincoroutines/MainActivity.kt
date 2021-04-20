package com.androidbootcamp.kotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidbootcamp.kotlincoroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartBigCalc.setOnClickListener {
            startActivity(Intent(this, BigCalcActivity::class.java))
        }

        binding.buttonStartCounter.setOnClickListener {
            startActivity(Intent(this, CounterActivity::class.java))
        }

        binding.buttonStartSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        val runnable = Runnable {
            Log.d("HANDLER", "inside post ${Thread.currentThread()}")
        }

        Log.d("HANDLER", "before ${Thread.currentThread()}")
        Thread {
            Log.d("HANDLER", "inside thread ${Thread.currentThread()}")
            Handler(Looper.getMainLooper()).post(runnable)
        }.start()
    }
}
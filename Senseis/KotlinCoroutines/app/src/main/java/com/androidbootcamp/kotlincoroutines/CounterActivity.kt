package com.androidbootcamp.kotlincoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.androidbootcamp.kotlincoroutines.databinding.ActivityCounterBinding
import kotlinx.coroutines.delay

class CounterActivity : AppCompatActivity() {

    private var value = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            while (true) {
                Log.d("CounterActivity", "increment value to ${++value}")
                binding.textCounter.text = value.toString()
                delay(1000)
            }
        }
    }
}
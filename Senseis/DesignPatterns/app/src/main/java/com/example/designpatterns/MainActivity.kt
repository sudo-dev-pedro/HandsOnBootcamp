package com.example.designpatterns

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.designpatterns.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.clickMe.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(this)
            val alertDialog = alertBuilder
                .setTitle("Android Bootcamp")
                .setMessage("Learning")
                .setIcon(R.drawable.ic_android)
                .setPositiveButton("POSITIVE") { _, _ -> }
                .create()

            alertDialog.show()
        }

        binding.clickMe2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("CLICKED")
                .setPositiveButton("OK") { _, _ -> }
                .create()
                .show()
        }
    }
}
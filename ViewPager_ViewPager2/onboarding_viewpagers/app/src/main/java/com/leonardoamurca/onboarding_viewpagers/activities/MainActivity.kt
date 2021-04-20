package com.leonardoamurca.onboarding_viewpagers.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.leonardoamurca.onboarding_viewpagers.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_example1 -> {
                val intent = Intent(applicationContext, OnboardingExample1Activity::class.java)
                startActivity(intent)
            }
            R.id.btn_example2 -> {
                val intent = Intent(applicationContext, OnboardingExample2Activity::class.java)
                startActivity(intent)
            }
            R.id.btn_example3 -> {
                val intent = Intent(applicationContext, OnboardingExample3Activity::class.java)
                startActivity(intent)
            }
            R.id.btn_example4 -> {
                val intent = Intent(applicationContext, OnboardingExample4Activity::class.java)
                startActivity(intent)
            }
        }
    }
}
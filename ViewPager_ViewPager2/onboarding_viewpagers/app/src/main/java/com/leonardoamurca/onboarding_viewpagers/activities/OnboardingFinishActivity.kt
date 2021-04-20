package com.leonardoamurca.onboarding_viewpagers.activities

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.leonardoamurca.onboarding_viewpagers.R

class OnboardingFinishActivity : AppCompatActivity() {
    private lateinit var btnStart: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_finish)
        btnStart = findViewById(R.id.layout_start)
        btnStart.setOnClickListener {
            finish()
        }
    }
}
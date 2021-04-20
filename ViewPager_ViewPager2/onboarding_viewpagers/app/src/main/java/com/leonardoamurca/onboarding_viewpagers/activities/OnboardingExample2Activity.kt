package com.leonardoamurca.onboarding_viewpagers.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.leonardoamurca.onboarding_viewpagers.R
import com.leonardoamurca.onboarding_viewpagers.adapter.OnboardingViewPagerAdapter
import com.leonardoamurca.onboarding_viewpagers.databinding.ActivityOnboardingExample1Binding
import com.leonardoamurca.onboarding_viewpagers.databinding.ActivityOnboardingExample2Binding

class OnboardingExample2Activity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var binding: ActivityOnboardingExample2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingExample2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        viewPager = binding.viewPagerTwo
        viewPager.adapter = OnboardingViewPagerAdapter(supportFragmentManager, this)

        val btnCreateAccount: Button = findViewById(R.id.btn_create_account)
        btnCreateAccount.setOnClickListener {
            finish()
        }
    }
}
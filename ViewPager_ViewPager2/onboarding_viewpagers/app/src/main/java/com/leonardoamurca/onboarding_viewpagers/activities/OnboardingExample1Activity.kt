package com.leonardoamurca.onboarding_viewpagers.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.leonardoamurca.onboarding_viewpagers.R
import com.leonardoamurca.onboarding_viewpagers.adapter.OnboardingViewPagerAdapter
import com.leonardoamurca.onboarding_viewpagers.databinding.ActivityOnboardingExample1Binding
import com.leonardoamurca.onboarding_viewpagers.fragments.OnboardingFragment
import java.util.concurrent.atomic.AtomicInteger

class OnboardingExample1Activity : AppCompatActivity() {

    // O que é um AtomicInteger?
    //private var pagePosition = AtomicInteger(0)
    private lateinit var viewPager: ViewPager
    private lateinit var binding: ActivityOnboardingExample1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingExample1Binding.inflate(layoutInflater)

        setContentView(binding.root)

        viewPager = binding.viewPager
        viewPager.adapter = OnboardingViewPagerAdapter(supportFragmentManager, this)

        val btnNextStep: Button = findViewById(R.id.btn_next_step)

        btnNextStep.setOnClickListener {
            // Se o item atual + 1 for maior do que o total de filhos - 1
            if (getItem(+1) > viewPager.childCount - 1) {
                finish()
                val intent = Intent(applicationContext, OnboardingFinishActivity::class.java)
                startActivity(intent)
            } else {
                viewPager.setCurrentItem(getItem(+1), true)
            }
        }

        val textSkip: TextView = findViewById(R.id.text_skip)

        textSkip.setOnClickListener {
            finish()
            val intent =
                Intent(applicationContext, OnboardingFinishActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getItem(item: Int): Int {
        return viewPager.currentItem + item
    }
}
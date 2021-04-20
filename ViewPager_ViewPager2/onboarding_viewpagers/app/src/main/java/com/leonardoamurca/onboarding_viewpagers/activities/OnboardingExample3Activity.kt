package com.leonardoamurca.onboarding_viewpagers.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.jaeger.library.StatusBarUtil
import com.leonardoamurca.onboarding_viewpagers.R
import com.leonardoamurca.onboarding_viewpagers.adapter.OnboardingThreeViewPagerAdapter
import com.leonardoamurca.onboarding_viewpagers.databinding.ActivityOnboardingExample3Binding
import com.leonardoamurca.onboarding_viewpagers.fragments.OnboardingFragment3
import java.util.concurrent.atomic.AtomicInteger

class OnboardingExample3Activity : AppCompatActivity() {

    private lateinit var bindingThree: ActivityOnboardingExample3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingThree = ActivityOnboardingExample3Binding.inflate(layoutInflater)
        setContentView(bindingThree.root)

        prepareAdapter()

        val callback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    2 -> setVisibilityWhenTwo()
                    else -> setVisibilityWhenBiggerThanTwo()
                }
            }
        }

        onCLickOnNextIcon()
        onCLickSkip()
        onCLickTextEnd()

        bindingThree.viewPagerThree.registerOnPageChangeCallback(callback)

        StatusBarUtil.setTranslucentForImageViewInFragment(this, null)
    }

    private fun prepareAdapter() {
        bindingThree.viewPagerThree.adapter = OnboardingThreeViewPagerAdapter(this, this)
        bindingThree.dotsIndicator.setViewPager2(bindingThree.viewPagerThree)
    }

    private fun onCLickTextEnd() {
        bindingThree.textEnd.setOnClickListener {
            finish()
        }
    }

    private fun onCLickSkip() {
        bindingThree.textSkip.setOnClickListener {
            finish()
        }
    }

    private fun onCLickOnNextIcon() {
        bindingThree.btnNextStep3.setOnClickListener {
            bindingThree.apply {
                btnNextStep3.visibility = View.GONE
                textEnd.visibility = View.VISIBLE
                textSkip.visibility = View.GONE
            }
        }
    }

    private fun setVisibilityWhenTwo() {
        bindingThree.btnNextStep3.visibility = View.GONE
        bindingThree.textEnd.visibility = View.VISIBLE
        bindingThree.textSkip.visibility = View.GONE
    }

    private fun setVisibilityWhenBiggerThanTwo() {
        bindingThree.btnNextStep3.visibility = View.VISIBLE
        bindingThree.textEnd.visibility = View.GONE
        bindingThree.textSkip.visibility = View.VISIBLE
    }
}
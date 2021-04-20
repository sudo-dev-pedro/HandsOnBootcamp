package com.leonardoamurca.onboarding_viewpagers.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.leonardoamurca.onboarding_viewpagers.R
import com.leonardoamurca.onboarding_viewpagers.adapter.OnboardingFourViewPagerAdapter
import com.leonardoamurca.onboarding_viewpagers.databinding.ActivityOnboardingExample4Binding

class OnboardingExample4Activity : AppCompatActivity() {

    private lateinit var bindingFour: ActivityOnboardingExample4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingFour = ActivityOnboardingExample4Binding.inflate(layoutInflater)
        setContentView(bindingFour.root)

        prepareAdapter()
        registerTheCallback()
        onClickOnNextOrFinish()
        // Não é necessário o botão de back se ele está na posição 0
        onClickOnBack()
    }

    private fun prepareAdapter() {
        bindingFour.viewPagerFour.adapter = OnboardingFourViewPagerAdapter(this, this)
        bindingFour.dotsIndicatorTwo.setViewPager2(bindingFour.viewPagerFour)
    }

    private fun registerTheCallback() {
        bindingFour.viewPagerFour.registerOnPageChangeCallback(provideCallback())
    }

    private fun provideCallback(): ViewPager2.OnPageChangeCallback {
        return object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    2 -> changeTextWhenPositionIsTwo()
                    else -> changeTextWhenPositionIsBiggerThanTwo()
                }
            }
        }
    }

    private fun onClickOnNextOrFinish() {
        bindingFour.btnNextStep4.setOnClickListener {
            if (bindingFour.viewPagerFour.currentItem == 2) {
                finish()
            } else {
                proceed()
            }
        }
    }

    private fun proceed() {
        bindingFour.viewPagerFour.currentItem = bindingFour.viewPagerFour.currentItem + 1
    }

    private fun onClickOnBack() {
        bindingFour.btnPreviousStep.setOnClickListener {
            back()
        }
    }

    private fun back() {
        bindingFour.viewPagerFour.currentItem = bindingFour.viewPagerFour.currentItem - 1
    }

    private fun changeTextWhenPositionIsTwo() {
        bindingFour.btnNextStep4.text = getText(R.string.finish)
    }

    private fun changeTextWhenPositionIsBiggerThanTwo() {
        bindingFour.btnNextStep4.text = getText(R.string.next)
    }
}
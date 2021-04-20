package com.leonardoamurca.onboarding_viewpagers.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.leonardoamurca.onboarding_viewpagers.R
import com.leonardoamurca.onboarding_viewpagers.activities.OnboardingExample3Activity
import com.leonardoamurca.onboarding_viewpagers.fragments.OnboardingFragment3

class OnboardingThreeViewPagerAdapter(
    activity: AppCompatActivity,
    private val context: OnboardingExample3Activity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = ITEMS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_1),
                context.resources.getString(R.string.description_onboarding_1),
                R.raw.lottie_splash_animation,
                "#4CAF50"
            )

            1 -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_2),
                context.resources.getString(R.string.description_onboarding_2),
                R.raw.lottie_girl_with_a_notebook,
                "#F44336"
            )

            2 -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_3),
                context.resources.getString(R.string.description_onboarding_3),
                R.raw.lottie_messaging,
                "#2196F3"
            )
            else -> null
        }!!
    }

    companion object {
        private const val ITEMS = 3
    }
}
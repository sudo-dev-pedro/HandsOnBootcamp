package com.leonardoamurca.onboarding_viewpagers.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.leonardoamurca.onboarding_viewpagers.R
import com.leonardoamurca.onboarding_viewpagers.activities.OnboardingExample4Activity
import com.leonardoamurca.onboarding_viewpagers.fragments.OnboardingFragment4

class OnboardingFourViewPagerAdapter(
    activity: AppCompatActivity,
    private val context: OnboardingExample4Activity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = ITEMS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment4.newInstance(
                context.resources.getString(R.string.title_onboarding_1),
                context.resources.getString(R.string.description_onboarding_1),
                R.raw.lottie_splash_animation
            )

            1 -> OnboardingFragment4.newInstance(
                context.resources.getString(R.string.title_onboarding_2),
                context.resources.getString(R.string.description_onboarding_2),
                R.raw.lottie_watch_videos
            )

            2 -> OnboardingFragment4.newInstance(
                context.resources.getString(R.string.title_onboarding_3),
                context.resources.getString(R.string.description_onboarding_3),
                R.raw.lottie_messaging
            )

            else -> null
        }!!
    }

    companion object {
        private const val ITEMS = 3
    }
}
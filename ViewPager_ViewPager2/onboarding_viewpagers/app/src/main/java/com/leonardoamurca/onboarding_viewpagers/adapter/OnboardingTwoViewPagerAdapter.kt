package com.leonardoamurca.onboarding_viewpagers.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.leonardoamurca.onboarding_viewpagers.R
import com.leonardoamurca.onboarding_viewpagers.fragments.OnboardingFragment2

class OnboardingTwoViewPagerAdapter(
    fragmentManager: FragmentManager,
    private var context: Context
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return NUM_ITENS
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment2.newInstance(
                context.resources.getString(R.string.title_onboarding_1),
                context.resources.getString(R.string.description_onboarding_1),
                R.raw.lottie_delivery_boy_bumpy_ride
            )
            1 -> OnboardingFragment2.newInstance(
                context.resources.getString(R.string.title_onboarding_2),
                context.resources.getString(R.string.description_onboarding_2),
                R.raw.lottie_developer
            )
            2 -> OnboardingFragment2.newInstance(
                context.resources.getString(R.string.title_onboarding_3),
                context.resources.getString(R.string.description_onboarding_3),
                R.raw.lottie_girl_with_a_notebook
            )
            else -> null
        }!!
    }

    companion object {
        const val NUM_ITENS = 3
    }
}
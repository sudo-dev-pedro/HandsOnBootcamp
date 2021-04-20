package com.raywenderlich.android.animaldoppelganger.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.raywenderlich.android.animaldoppelganger.DoppelgangerFragment

class DoppelgangerAdapter(
        activity: AppCompatActivity,
        private val itemsCount: Int
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return DoppelgangerFragment.getInstance(position)
    }
}
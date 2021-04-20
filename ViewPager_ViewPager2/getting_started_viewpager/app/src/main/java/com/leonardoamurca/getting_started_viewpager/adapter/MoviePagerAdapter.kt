package com.leonardoamurca.getting_started_viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.leonardoamurca.getting_started_viewpager.Movie
import com.leonardoamurca.getting_started_viewpager.MovieFragment

// O Construtor está depreciado por isso ele tacha!
class MoviePagerAdapter(
    fragmentManager: FragmentManager,
    private val movies: List<Movie>
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Fragment {
        return MovieFragment.newInstance(movies[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return movies[position % movies.size].title
    }

}
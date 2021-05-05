package br.com.handson5.adapter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.handson5.data.Movie
import br.com.handson5.ui.slider.MoviesFragment

class SliderViewPagerAdapter(
    activity: AppCompatActivity,
    private val moviesList: List<Movie>
) : FragmentStateAdapter(activity) {

    private val bundle = Bundle()

    override fun getItemCount(): Int = moviesList.size

    override fun createFragment(position: Int): Fragment {
        bundle.putSerializable(MoviesFragment.EXTRA_MOVIES_ARGUMENT, moviesList[position])

        return MoviesFragment().apply {
            arguments = bundle
        }
    }
}
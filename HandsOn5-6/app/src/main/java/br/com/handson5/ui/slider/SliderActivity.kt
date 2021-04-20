package br.com.handson5.ui.slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.handson5.R
import br.com.handson5.adapter.SliderViewPagerAdapter
import br.com.handson5.data.Movie
import br.com.handson5.databinding.ActivitySliderBinding

class SliderActivity : AppCompatActivity() {

    private lateinit var moviesList: List<Movie>
    private lateinit var sliderBinding: ActivitySliderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sliderBinding = ActivitySliderBinding.inflate(layoutInflater)
        setContentView(sliderBinding.root)

        moviesList = intent.getSerializableExtra(EXTRA_MOVIES) as List<Movie>

        prepareAdapter()
    }

    override fun onBackPressed() {
        if (sliderBinding.viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            sliderBinding.viewPager.currentItem = sliderBinding.viewPager.currentItem - 1
        }
    }

    private fun prepareAdapter() {
        sliderBinding.viewPager.adapter = SliderViewPagerAdapter(this, moviesList)
    }

    companion object {
        const val EXTRA_MOVIES = "Movies List"
    }
}
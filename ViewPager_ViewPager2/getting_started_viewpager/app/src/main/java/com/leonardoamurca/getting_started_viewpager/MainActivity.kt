package com.leonardoamurca.getting_started_viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.viewpager.widget.ViewPager
import com.leonardoamurca.getting_started_viewpager.adapter.MoviePagerAdapter
import com.leonardoamurca.getting_started_viewpager.databinding.ActivityMainBinding
import com.leonardoamurca.getting_started_viewpager.transformer.ZoomOutPageTransformer
import com.nshmura.recyclertablayout.RecyclerTabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var view: View
    private lateinit var viewPager: ViewPager
    private lateinit var recyclerTabLayout: RecyclerTabLayout
    private val zoomOut by lazy {
        ZoomOutPageTransformer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        view = mainBinding.root

        setContentView(view)
        val movies = MovieHelper.getMoviesFromJson(
            "movies.json",
            this
        )

        viewPager = mainBinding.viewpager
        recyclerTabLayout = mainBinding.recyclerTabLayout

        viewPager.adapter = MoviePagerAdapter(supportFragmentManager, movies)
        viewPager.setPageTransformer(false, zoomOut)
        recyclerTabLayout.setUpWithViewPager(viewPager)
    }
}
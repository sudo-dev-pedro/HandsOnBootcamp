package br.com.handson5.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.handson5.adapter.MovieAdapter
import br.com.handson5.data.Movie
import br.com.handson5.databinding.ActivityMainBinding
import br.com.handson5.ui.moviedetails.MovieDetailActivity
import br.com.handson5.ui.moviedetails.MovieDetailActivity.Companion.EXTRA_MOVIE_DESCRIPTION
import br.com.handson5.ui.moviedetails.MovieDetailActivity.Companion.EXTRA_MOVIE_IMAGE
import br.com.handson5.ui.moviedetails.MovieDetailActivity.Companion.EXTRA_MOVIE_NAME
import br.com.handson5.ui.slider.SliderActivity
import br.com.handson5.ui.slider.SliderActivity.Companion.EXTRA_MOVIES
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        movieAdapter = MovieAdapter(listOf()) {
            navigateToDetails(it)
        }

        initViews()

        observeMainViewModel()

        onClickLoadButton()
        onClickEraseButton()
        onClickViewPagerButton()
    }

    private fun observeMainViewModel() {

        mainViewModel.showLoadingLiveData.observe(this) {
            mainBinding.loading.visibility = View.VISIBLE
        }

        mainViewModel.hideLoadingLiveData.observe(this) {
            mainBinding.loading.visibility = View.GONE
        }

        mainViewModel.showMessageError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        mainViewModel.moviesLiveData.observe(this) {
            movieAdapter.updateMovieList(it)
        }
    }

    private fun onClickLoadButton() {
        mainBinding.btnLoadMovies.setOnClickListener {
            mainViewModel.setIsLoading(true)
            mainViewModel.searchMovies()
        }
    }

    private fun onClickEraseButton() {
        mainBinding.btnClearMovies.setOnClickListener {
            mainViewModel.setIsLoading(false)
            movieAdapter.updateMovieList(emptyList())
        }
    }

    private fun onClickViewPagerButton() {
        mainBinding.btnViewPager.setOnClickListener {
            navigateToViewPager()
        }
    }

    private fun navigateToDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)

        intent.putExtra(EXTRA_MOVIE_IMAGE, movie.image)
        intent.putExtra(EXTRA_MOVIE_NAME, movie.title)
        intent.putExtra(EXTRA_MOVIE_DESCRIPTION, movie.description)

        startActivity(intent)
    }

    private fun navigateToViewPager() {
        val intent = Intent(
            this,
            SliderActivity::class.java
        )

        intent.putExtra(EXTRA_MOVIES, movieAdapter.getList() as ArrayList)
        startActivity(intent)
    }

    private fun initViews() {
        recyclerViewMovies = mainBinding.rvMoviesList
        recyclerViewMovies.adapter = movieAdapter
        recyclerViewMovies.setHasFixedSize(true)
    }
}
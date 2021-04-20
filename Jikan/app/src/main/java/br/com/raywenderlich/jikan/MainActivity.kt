package br.com.raywenderlich.jikan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.jikan.adapters.AnimesAdapter
import br.com.raywenderlich.jikan.databinding.ActivityMainBinding
import br.com.raywenderlich.jikan.extensions.toRequestResult
import br.com.raywenderlich.jikan.handlers.RequestResultError
import br.com.raywenderlich.jikan.handlers.RequestResultNotFound
import br.com.raywenderlich.jikan.handlers.RequestResultSuccess
import br.com.raywenderlich.jikan.models.Anime
import br.com.raywenderlich.jikan.services.JikanAPI
import br.com.raywenderlich.jikan.services.SearchService
import br.com.raywenderlich.jikan.viewmodel.JikanViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var view: View
    private lateinit var searchService: SearchService
    private lateinit var recyclerViewAnimes: RecyclerView
    private lateinit var animesList: MutableList<Anime>
    private lateinit var itemIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        view = mainBinding.root

        setContentView(view)

        searchService = JikanAPI.jikanAPIConfig()
        initViews()
        itemIntent = Intent()

        animesList = mutableListOf()
        animesList.clear()
        initAdapter(emptyList())

        mainBinding.btnSearch.setOnClickListener {
            dispatcherRequestAnime()
        }
    }

    private fun dispatcherRequestAnime() {
        CoroutineScope(Dispatchers.Main).launch {
            requestAnimes()
        }
    }

    private suspend fun requestAnimes() {
        val text = mainBinding.edtName.text.toString()

        when (val foundAnimes = searchService.search(text).toRequestResult()) {
            is RequestResultSuccess -> {
                foundAnimes.data.results.forEach {
                    animesList.add(it)
                }

                initAdapter(animesList)
            }

            is RequestResultNotFound -> {
                mainBinding.scrollContent.text = foundAnimes.errorMessage
            }

            is RequestResultError -> {
                mainBinding.scrollContent.text = "Erro genérico!"
            }
        }
    }

    private fun initAdapter(animesList: List<Anime>) {
        recyclerViewAnimes.adapter = AnimesAdapter(animesList) {
            navigateToAnimeDetails(it)
        }
    }

    private fun initViews() {
        recyclerViewAnimes = mainBinding.rvAnimesList
        recyclerViewAnimes.setHasFixedSize(true)
    }

    private fun navigateToAnimeDetails(anime: Anime) {
        itemIntent = Intent(
            this,
            AnimeDetailActivity::class.java
        ).apply {
            putExtra("anime", anime)
        }

        startActivity(itemIntent)
    }
}
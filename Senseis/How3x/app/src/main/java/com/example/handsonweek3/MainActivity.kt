package com.example.handsonweek3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import com.example.handsonweek3.DetailsActivity.Companion.EXTRA_ANIME_ID
import com.example.handsonweek3.adapters.AnimeResultAdapter
import com.example.handsonweek3.extensions.toRequestResult
import com.example.handsonweek3.handlers.RequestResultError
import com.example.handsonweek3.handlers.RequestResultNotFound
import com.example.handsonweek3.handlers.RequestResultSuccess
import com.example.handsonweek3.interceptors.LogInterceptor
import com.example.handsonweek3.services.SearchAnimeResult
import com.example.handsonweek3.services.SearchService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var searchService: SearchService

    private lateinit var recyclerViewAdapter: AnimeResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        prepareView()

        searchService = buildSearchService()
    }

    private fun prepareView() {
        // Creating recycler view adapter with an empty list.
        recyclerViewAdapter = AnimeResultAdapter(emptyList()) { navigateToAnimeDetails(it) }
        findViewById<RecyclerView>(R.id.recyclerView).adapter = recyclerViewAdapter

        // Adding click listener to search button.
        findViewById<Button>(R.id.searchButton).setOnClickListener { dispatchRequestAnimes() }

    }

    private fun dispatchRequestAnimes() {
        // Open a new thread to avoid UI freeze.
        CoroutineScope(Dispatchers.Main).launch { requestAnimes() }
    }

    private suspend fun requestAnimes() {
        val text = findViewById<AppCompatEditText>(R.id.searchText).text.toString()

        // Consume the API passing the search query and wait for the result.
        when (val foundAnimes = searchService.search(text).toRequestResult()) {
            is RequestResultSuccess -> {
                recyclerViewAdapter.updateItems(foundAnimes.data.results)
            }

            is RequestResultNotFound -> {
                recyclerViewAdapter.updateItems(emptyList())
                Toast.makeText(this, "Cannot find any anime with the provided text.", Toast.LENGTH_SHORT).show()
            }

            is RequestResultError -> {
                recyclerViewAdapter.updateItems(emptyList())
                Toast.makeText(this, foundAnimes.message, Toast.LENGTH_SHORT).show()
            }
        }

        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun buildSearchService(): SearchService {
        // Create the moshi instance to help on parsing the results to local objects.
        val moshi = Moshi.Builder().build()

        val okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(LogInterceptor())
                .build()

        // Create the retrofit instance defining the API base URl and configuring moshi on it.
        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        // Generate the needed service instance to consume.
        return retrofit.create(SearchService::class.java)
    }

    private fun navigateToAnimeDetails(anime: SearchAnimeResult) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_ANIME_ID, anime.id)

        startActivity(intent)
    }
}
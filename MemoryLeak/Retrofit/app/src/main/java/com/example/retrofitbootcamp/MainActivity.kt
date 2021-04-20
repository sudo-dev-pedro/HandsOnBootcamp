package com.example.retrofitbootcamp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.retrofitbootcamp.extensions.toRequestResult
import com.example.retrofitbootcamp.handlers.RequestResultError
import com.example.retrofitbootcamp.handlers.RequestResultNotFound
import com.example.retrofitbootcamp.handlers.RequestResultSuccess
import com.example.retrofitbootcamp.interceptors.LogInterceptor
import com.example.retrofitbootcamp.services.jikan.SearchService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var searchService: SearchService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        searchService = buildSearchService()

        findViewById<Button>(R.id.searchButton).setOnClickListener {
            dispatchRequestAnimes()
        }

        findViewById<Button>(R.id.triggerEvents).setOnClickListener {
            GlobalSingleton.triggerAllListenersEvent()
        }

        findViewById<Button>(R.id.leakActivity).setOnClickListener {
            startActivity(Intent(this, LeakingActivity::class.java))
        }
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
                // Parse the result and put it inside a TextView.
                var result = ""
                foundAnimes.data.results.forEach {
                    result += it.title + "\n" + it.synopsis + "\n\n\n"
                }

                findViewById<TextView>(R.id.contentTextView).text = result
            }

            is RequestResultNotFound -> {
                findViewById<TextView>(R.id.contentTextView).text = "Cannot find any anime with the provided text."
            }

            is RequestResultError -> {
                findViewById<TextView>(R.id.contentTextView).text = foundAnimes.message
            }
        }
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
}
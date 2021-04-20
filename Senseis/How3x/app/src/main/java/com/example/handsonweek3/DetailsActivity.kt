package com.example.handsonweek3

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.handsonweek3.adapters.AnimeCharacterResultAdapter
import com.example.handsonweek3.adapters.AnimeResultAdapter
import com.example.handsonweek3.extensions.toRequestResult
import com.example.handsonweek3.handlers.RequestResultError
import com.example.handsonweek3.handlers.RequestResultSuccess
import com.example.handsonweek3.interceptors.LogInterceptor
import com.example.handsonweek3.services.AnimeCharacterResult
import com.example.handsonweek3.services.AnimeResult
import com.example.handsonweek3.services.AnimeService
import com.example.handsonweek3.tasks.doAsync
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStream
import java.net.URL

class DetailsActivity : AppCompatActivity() {

    private lateinit var animeService: AnimeService

    private lateinit var charactersRecyclerViewAdapter: AnimeCharacterResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)
        prepareView()

        animeService = buildAnimeService()

        CoroutineScope(Dispatchers.Main).launch { requestAnimeDetails() }
    }

    private fun prepareView() {
        title = ""

        // Creating recycler view adapter with an empty list.
        charactersRecyclerViewAdapter = AnimeCharacterResultAdapter(emptyList())
        with(findViewById<RecyclerView>(R.id.charactersRecyclerView)) {
            adapter = charactersRecyclerViewAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun buildAnimeService(): AnimeService {
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
        return retrofit.create(AnimeService::class.java)
    }

    private suspend fun requestAnimeDetails() {
        val animeId = intent.getIntExtra(EXTRA_ANIME_ID, 0)

        // Get the anime details to fill the screen.
        when (val result = animeService.getDetails(animeId).toRequestResult()) {
            is RequestResultSuccess -> {
                // Start building the anime details.
                prepareAnimeDetailsView(result.data)

                // Dispatch the request to the anime characters.
                requestAnimeCharacters(animeId)
            }

            is RequestResultError -> {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun requestAnimeCharacters(animeId: Int) {
        // Get the anime characters and parse the result.
        when (val result = animeService.getCharactersAndStaff(animeId).toRequestResult()) {
            is RequestResultSuccess -> {
                // Start building the recycler view with the contents.
                prepareAnimeCharactersRecyclerView(result.data.characters)
            }

            is RequestResultError -> {
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun prepareAnimeDetailsView(value: AnimeResult) {
        doAsync {
            // Downloading the bitmap inside the task.
            val bitmap = BitmapFactory.decodeStream(URL(value.imageUrl).content as InputStream)

            // Setting the downloaded image to the ImageView.
            with(findViewById<ImageView>(R.id.bannerImage)) { post { setImageBitmap(bitmap) } }
        }

        title = value.title

        findViewById<TextView>(R.id.name).text = value.title

        findViewById<View>(R.id.animeTitleSeparator).visibility = VISIBLE

        findViewById<TextView>(R.id.animeInfo).text =
            "Episodes: ${value.episodes} | Score: ${value.score} | Airing: ${value.airing.toYesNo()}"

        findViewById<TextView>(R.id.description).text = value.synopsis
    }

    private fun prepareAnimeCharactersRecyclerView(value: List<AnimeCharacterResult>) {
        findViewById<View>(R.id.charactersTitle).visibility = VISIBLE
        findViewById<View>(R.id.charactersTitleSeparator).visibility = VISIBLE

        charactersRecyclerViewAdapter.updateItems(value)
        charactersRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun Boolean.toYesNo() = if (this) "Yes" else "No"

    companion object {
         const val EXTRA_ANIME_ID = "EXTRA_ANIME_ID"
    }
}
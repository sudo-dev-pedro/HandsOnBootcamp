package com.androidbootcamp.kotlincoroutines.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.androidbootcamp.kotlincoroutines.extensions.toRequestResult
import com.androidbootcamp.kotlincoroutines.handlers.RequestResult
import com.androidbootcamp.kotlincoroutines.handlers.RequestResultError
import com.androidbootcamp.kotlincoroutines.handlers.RequestResultNotFound
import com.androidbootcamp.kotlincoroutines.handlers.RequestResultSuccess
import com.androidbootcamp.kotlincoroutines.services.jikan.SearchResult
import com.androidbootcamp.kotlincoroutines.services.jikan.SearchService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel : ViewModel() {

    private lateinit var searchService: SearchService

    // Só a minha ViewModel pode ver e alterar isso!
    private val _response = MutableLiveData<String>()

    // Isso fica exposto para quem tiver a minha VM instanciada.
    val response: LiveData<String>
        get() = _response

    private var searchJob: Job? = null

    @ExperimentalCoroutinesApi
    private val searchChannel = ConflatedBroadcastChannel<String>()

    init {
        viewModelScope.launch {
            searchChannel.asFlow()
                .debounce(1000)
                .collect {
                    internalSearchByName(it)
                }
        }
    }

    @ExperimentalCoroutinesApi
    fun searchByName(text: String) {
        viewModelScope.launch {
            searchChannel.send(text)
        }
    }

    private fun internalSearchByName(text: String) {
        Log.d("REQUEST", text)

        searchService = buildSearchService()

        // Esse código tem um problema:
        /*
        * Mesmo eu cancelando o job ele ainda manda vezes o que foi digitado para cá
        * Fazendo o uso de um Channel, isso não ocorre, pois há um debounce que emite a útima coisa
        * desejada depois desse tempo informado.
        * */
        searchJob?.cancel()

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            // IO
            // O .execute é SÍNCRONO
            val response = searchService.search(text).execute()

            // Jogo a response para o meu contexto de Main
            withContext(Dispatchers.Main) {
                handleResult(response.toRequestResult())
            }
        }
    }

    fun checkJob(jobCounter: Int): Boolean {
        if (jobCounter > 0) {
            return true
        }
        return false
    }

    private fun handleResult(foundAnimes: RequestResult<SearchResult>) {
        when (foundAnimes) {
            is RequestResultSuccess -> {
                // Parse the result and put it inside a TextView.
                var result = ""
                foundAnimes.data.results.forEach {
                    result += it.title + "\n" + it.synopsis + "\n\n\n"
                }

                _response.value = result
            }

            is RequestResultNotFound -> {
                _response.value = "Cannot find any anime with the provided text."
            }

            is RequestResultError -> {
                _response.value = foundAnimes.message
            }
        }
    }

    private fun buildSearchService(): SearchService {
        // Create the moshi instance to help on parsing the results to local objects.
        val moshi = Moshi.Builder().build()

        val okHttpClient = OkHttpClient().newBuilder()
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
package br.com.raywenderlich.jikan.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.raywenderlich.jikan.models.Anime
import br.com.raywenderlich.jikan.models.SearchResult
import br.com.raywenderlich.jikan.services.JikanAPI
import br.com.raywenderlich.jikan.services.SearchService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JikanRepository {
    fun search(name: String): MutableLiveData<SearchResult> {

        val jikanClient = JikanAPI.jikanAPIConfig()
        val callback = jikanClient.search(name)

        val jikanLiveData = MutableLiveData<SearchResult>()

        callback.enqueue(
            object : Callback<SearchResult> {
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Log.d("Erro!", "Falhamos, parça!")
                }

                override fun onResponse(
                    call: Call<SearchResult>, response: Response<SearchResult>
                ) {
                    if (response.isSuccessful) {
                        jikanLiveData.value = response.body()
                    }
                }
            })

        return jikanLiveData
    }

}
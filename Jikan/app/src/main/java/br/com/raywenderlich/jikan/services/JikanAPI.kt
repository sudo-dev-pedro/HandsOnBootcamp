package br.com.raywenderlich.jikan.services

import br.com.raywenderlich.jikan.interceptors.GenericInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class JikanAPI {
    companion object {
        val moshi = Moshi.Builder().build()

        private fun buildOkClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(GenericInterceptor())
                .build()

        fun jikanAPIConfig(): SearchService {
            val retrofit = Retrofit.Builder()
                .client(buildOkClient())
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit.create(SearchService::class.java)
        }

        fun jikanAPIConfigCharacter(): AnimeService {
            val retrofit = Retrofit.Builder()
                .client(buildOkClient())
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit.create(AnimeService::class.java)
        }
    }
}
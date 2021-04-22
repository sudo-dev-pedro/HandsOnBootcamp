package br.com.raywenderlich.jikan.services

import br.com.raywenderlich.jikan.interceptors.GenericInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {

    single(named(MOSHI)) {
        Moshi
            .Builder()
            .build()
    }

    single(named(INTERCEPTOR)) {
        OkHttpClient.Builder()
            .addInterceptor(GenericInterceptor())
            .build()
    }

    single(named(RETROFIT)) {
        Retrofit.Builder()
            .client(get(named(INTERCEPTOR)))
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get(named(MOSHI))))
            .build()
    }

    single {
        get<Retrofit>(named(RETROFIT)).create(SearchService::class.java)
    }

    single {
        get<Retrofit>(named(RETROFIT)).create(AnimeService::class.java)
    }

}

const val RETROFIT = "Retrofit Dependency"
const val INTERCEPTOR = "Interceptor"
const val MOSHI = "Moshi"
const val BASE_URL = "https://api.jikan.moe/v3/"
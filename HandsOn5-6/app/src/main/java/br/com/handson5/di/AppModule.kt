package br.com.handson5.di

import br.com.handson5.repository.MovieApi
import br.com.handson5.repository.MovieRepository
import br.com.handson5.ui.main.MainViewModel
import com.squareup.moshi.Moshi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single(named(MOSHI)) {
        Moshi
            .Builder()
            .build()
    }

    single(named(RETROFIT)) {
        Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory
                    .create(get<Moshi>(named(MOSHI)))
            )
            .baseUrl(MovieRepository.URL)
            .build()
    }

    single {
        get<Retrofit>(named(RETROFIT))
            .create(MovieApi::class.java)
    }

    single { MovieRepository() }

    viewModel { MainViewModel(moviesRepository = get()) }
}

const val RETROFIT = "Retrofit Dependency"
const val MOSHI = "Moshi"
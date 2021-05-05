package br.com.handson5.di

import br.com.handson5.App.Companion.databaseManager
import br.com.handson5.database.repository.MovieEntityRepository
import br.com.handson5.database.repository.UserRepository
import br.com.handson5.repository.MovieApi
import br.com.handson5.repository.MovieRepository
import br.com.handson5.repository.MovieRepository.Companion.URL
import br.com.handson5.ui.login.LoginViewModel
import br.com.handson5.ui.main.MainViewModel
import br.com.handson5.ui.moviedetails.MovieDetailsViewModel
import br.com.handson5.ui.register.RegisterViewModel
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
                    .create(get(named(MOSHI)))
            )
            .baseUrl(URL)
            .build()
    }

    single {
        get<Retrofit>(named(RETROFIT))
            .create(MovieApi::class.java)
    }
}

val repositoryModule = module {
    single { MovieRepository() }

    single { databaseManager.movieDao() }

    single { MovieEntityRepository(get()) }

    single { databaseManager.userDao() }

    single { UserRepository(userDao = get()) }
}

val viewModelsModule = module {
    viewModel { MainViewModel(moviesRepository = get(), moviesEntityRepository = get()) }
    viewModel { MovieDetailsViewModel(moviesEntityRepository = get()) }
    viewModel { LoginViewModel(userRepository = get()) }
    viewModel { RegisterViewModel(userRepository = get()) }
}

const val RETROFIT = "Retrofit Dependency"
const val MOSHI = "Moshi"
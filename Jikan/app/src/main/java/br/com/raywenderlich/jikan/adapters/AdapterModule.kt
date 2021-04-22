package br.com.raywenderlich.jikan.adapters

import org.koin.dsl.module

val adapterModule = module {
    factory {
        CharacterAdapter()
    }

    factory {
        AnimesAdapter()
    }
}
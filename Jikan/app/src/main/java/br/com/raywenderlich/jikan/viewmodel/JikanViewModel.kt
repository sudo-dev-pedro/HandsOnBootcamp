package br.com.raywenderlich.jikan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.raywenderlich.jikan.models.SearchResult
import br.com.raywenderlich.jikan.repository.JikanRepository

class JikanViewModel(
    val jikanRepository: JikanRepository = JikanRepository()
) : ViewModel() {

    private var characterLiveData = MutableLiveData<SearchResult>()

    fun searchAnimeByName(name: String): MutableLiveData<SearchResult> {
        characterLiveData = jikanRepository.search(name)

        return characterLiveData
    }
}
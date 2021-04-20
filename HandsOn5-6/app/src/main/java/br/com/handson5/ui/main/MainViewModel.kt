package br.com.handson5.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.handson5.data.Movie
import br.com.handson5.repository.MovieDataSource
import br.com.handson5.repository.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val moviesRepository: MovieRepository
) : ViewModel(), MovieDataSource.LoadMoviesCallback {

    private val _showLoadingLiveData = MutableLiveData<Unit>()
    val showLoadingLiveData: LiveData<Unit>
        get() = _showLoadingLiveData

    private val _hideLoadingLiveData = MutableLiveData<Unit>()
    val hideLoadingLiveData: LiveData<Unit>
        get() = _hideLoadingLiveData

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>>
        get() = _moviesLiveData

    private val _showMessageError = MutableLiveData<String>()
    val showMessageError: LiveData<String>
        get() = _showMessageError

    fun setIsLoading(loading: Boolean) {
        if (loading) {
            _showLoadingLiveData.postValue(Unit)
        } else {
            _hideLoadingLiveData.postValue(Unit)
        }
    }

    fun searchMovies() {
        val callback = this
        viewModelScope.launch {
            moviesRepository.getMovies(callback)
        }
    }

    override fun onMoviesLoaded(movies: List<Movie>) {
        _moviesLiveData.postValue(movies)
        setIsLoading(false)
    }

    override fun onDataNotAvailable() {
        _showMessageError.postValue("Nada encontrado! (404)")
        setIsLoading(false)
    }

    override fun onError() {
        _showMessageError.postValue("Error na API!")
        setIsLoading(false)
    }
}
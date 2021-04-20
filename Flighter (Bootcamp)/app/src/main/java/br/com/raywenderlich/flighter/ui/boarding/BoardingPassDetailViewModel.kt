package br.com.raywenderlich.flighter.ui.boarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BoardingPassDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Show the boarding pass detail"
    }

    val text: LiveData<String> = _text
}
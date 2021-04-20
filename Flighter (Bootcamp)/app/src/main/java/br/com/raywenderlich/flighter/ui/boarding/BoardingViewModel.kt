package br.com.raywenderlich.flighter.ui.boarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BoardingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Show the list of boarding pass "
    }
    val text: LiveData<String> = _text
}
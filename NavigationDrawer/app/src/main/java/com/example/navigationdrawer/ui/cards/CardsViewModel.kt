package com.example.navigationdrawer.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CardsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Meus Cartões"
    }
    val text: LiveData<String> = _text

}
package com.example.navigationdrawer.ui.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatementViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Extrato"
    }
    val text: LiveData<String> = _text
}
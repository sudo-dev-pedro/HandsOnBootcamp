package com.example.navigationdrawer.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Agendamentos Pendentes"
    }
    val text: LiveData<String> = _text
}
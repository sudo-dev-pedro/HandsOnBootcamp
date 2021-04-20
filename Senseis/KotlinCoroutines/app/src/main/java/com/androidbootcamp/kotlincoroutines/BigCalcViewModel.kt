package com.androidbootcamp.kotlincoroutines

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class BigCalcViewModel : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    /*
    De acordo com o Anderson, essa já é a melhor maneira de fazer algo relacionado a isso
    */
    fun startBigCalc() {
        viewModelScope.launch(Dispatchers.Default) {

            var result = BigDecimal(Random.nextDouble())

            repeat(5000) {
                result = result.multiply(BigDecimal(Random.nextDouble()))
            }

            val resultText = result.toString()

            withContext(Dispatchers.Main) {
                _result.value = resultText
            }
        }
    }
}
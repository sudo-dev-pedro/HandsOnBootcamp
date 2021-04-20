package br.com.raywenderlich.calculatorbootcamp.app

import kotlin.math.pow

class SecretConversion(private val n: Int) {
    fun makeConversion(a: Int) = a.toFloat().pow(n)
    fun makeConversion2(a: Int) = a.toFloat().pow(n)
}
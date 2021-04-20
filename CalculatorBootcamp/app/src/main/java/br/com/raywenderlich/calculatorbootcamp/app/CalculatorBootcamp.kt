package br.com.raywenderlich.calculatorbootcamp.app

import java.lang.Math.abs

class CalculatorBootcamp(
    private val secretConversion: SecretConversion
) {

    fun addTwoNumbers(a: Int, b: Int) = a + b
    fun subtractTwoNumbers(a: Int, b: Int) = a - b
    fun multiplyTwoNumbers(a: Int, b: Int) = a * b
    fun divideTwoNumbers(a: Int, b: Int) = a / b

    // Número absoluto
    fun absolute(a: Int) = abs(a)
    fun isPositive(a: Int) = a > 0
    fun isNegative(a: Int) = a < 0
    fun isZero(a: Int) = a == 0

    //Exemplos mais próximos da realidade
    fun myCrazyCalculation(a: Int, b: Int) = when {
        isMyFavoriteNumber(a) -> {
            multiplyTwoNumbers(a, b)
        }
        isPositive(a) -> CRAZY_NUMBER_1
        else -> CRAZY_NUMBER_2
    }

    private fun isMyFavoriteNumber(a: Int) = a == MY_FAVORITE_NUMBER

    fun addAndMakeSecretConversion(a: Int, b: Int) = secretConversion.makeConversion(a + b)

    companion object {
        const val MY_FAVORITE_NUMBER = 14
        const val CRAZY_NUMBER_1 = 111
        const val CRAZY_NUMBER_2 = 222
    }
}
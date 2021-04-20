package br.com.raywenderlich.calculatorbootcamp

import br.com.raywenderlich.calculatorbootcamp.app.CalculatorBootcamp
import br.com.raywenderlich.calculatorbootcamp.app.CalculatorBootcamp.Companion.CRAZY_NUMBER_2
import br.com.raywenderlich.calculatorbootcamp.app.CalculatorBootcamp.Companion.MY_FAVORITE_NUMBER
import br.com.raywenderlich.calculatorbootcamp.app.SecretConversion
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.ArithmeticException

class CalculatorBootcampTest {

    private lateinit var calculatorBootcamp: CalculatorBootcamp

    // Declaro como MockK e a biblioteca faz um dublê do que desejo!
    @MockK
    private lateinit var secretConversion: SecretConversion

    @Before
    fun setup() {
        // O Dublê não sabe bem o que fazer!
        MockKAnnotations.init(this)

        calculatorBootcamp = CalculatorBootcamp(secretConversion)
    }

    @Test
    fun addTwoNumbers() {
        val response = calculatorBootcamp.addTwoNumbers(2, 3)

        assertEquals(5, response)
    }

    @Test
    fun subtractTwoNumbers() {
        val response = calculatorBootcamp.subtractTwoNumbers(2, 3)

        assertEquals(-1, response)
    }

    @Test
    fun multiplyTwoNumbers() {
        val response = calculatorBootcamp.multiplyTwoNumbers(4, 3)

        assertEquals(12, response)
    }

    @Test
    fun divideTwoNumbers() {
        val response = calculatorBootcamp.divideTwoNumbers(12, 3)

        assertEquals(4, response)
    }

    @Test(expected = ArithmeticException::class)
    fun checkIfTheDivisionIsByZero() {
        calculatorBootcamp.divideTwoNumbers(4, 0)
    }

    @Test
    fun checkIfTheNumberIsAbsolute() {
        val response = calculatorBootcamp.absolute(8)

        assertEquals(8, response)
    }

    @Test
    fun checkIfTheNumberIsPositive() {
        val response = calculatorBootcamp.isPositive(12)

        assertTrue(response)
    }

    @Test
    fun checkIfTheNumberIsNegative() {
        val response = calculatorBootcamp.isNegative(-3)

        assertTrue(response)
    }

    @Test
    fun `given zero - when zero is called - then show true`() {
        val response = calculatorBootcamp.isZero(0)

        assertTrue(response)
    }

    @Test
    fun `given a number - when number is called check if is my favorite number - then show the multiply result`() {
        val a = MY_FAVORITE_NUMBER
        val b = 12
        val response = calculatorBootcamp.myCrazyCalculation(a, b)

        assertEquals(response, 168)
    }

    @Test
    fun `given a number - when number is called check if the number is positive - then show the crazy number 1`() {
        val response = calculatorBootcamp.myCrazyCalculation(1, 12)

        assertEquals(response, 111)
    }

    @Test
    fun `given a number - when number is called check if the number is negative - then show the crazy number 2`() {
        val response = calculatorBootcamp.myCrazyCalculation(-1, 12)

        assertEquals(CRAZY_NUMBER_2, response)
    }

    @Test
    fun `given two numbers - when numbers are called make the add and the secret conversion - then show the result`() {
        val expectedValue = 1238F
        // Aqui eu digo para o dublê o que ele precisa fazer
        val a = 4
        val b = 6
        every { secretConversion.makeConversion(a + b) } returns expectedValue

        val response = calculatorBootcamp.addAndMakeSecretConversion(a, b)

        assertEquals(expectedValue, response)

        // Verifique se o dublê é chamado.
        // Verifique quantas vezes ele é chamado por meio do exactly.
        verify(exactly = 1) {
            secretConversion.makeConversion(a + b)
        }
        /*
        Isso aqui não é COMUM!
        Não fica checando se tudo que se tem dentro de uma classe está sendo chamado!

        verify(exactly = 0) {
            secretConversion.makeConversion2(any())
        }
        */
    }
}
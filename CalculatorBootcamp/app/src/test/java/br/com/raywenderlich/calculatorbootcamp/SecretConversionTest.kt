package br.com.raywenderlich.calculatorbootcamp

import br.com.raywenderlich.calculatorbootcamp.app.SecretConversion
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SecretConversionTest {

    private lateinit var secretConversion: SecretConversion

    @Before
    fun setup() {
        secretConversion = SecretConversion(3)
    }

    @Test
    fun `given a number - when called the number make the secret conversion - then show the result`() {
        val result = secretConversion.makeConversion(6)

        Assert.assertEquals(36.0f, result)
    }
}
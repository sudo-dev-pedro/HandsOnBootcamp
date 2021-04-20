package com.androidbootcamp.kotlincoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class BuildersExampleTest {

    @Test
    fun testSequential() = runBlocking {
        // measureTimeMillis me calcula quanto tempo uma execução levou.
        val timeMillis = measureTimeMillis {
            val result = doBigMath()
            println(result)
            val result2 = doBigMath()
            println(result2)
            val result3 = doBigMath()
            println(result3)
            val result4 = doBigMath()
            println(result4)
            val result5 = doBigMath()
            println(result5)
            val result6 = doBigMath()
            println(result6)
        }
        println("took $timeMillis")
    }

    @Test
    fun testAsync() = runBlocking {
        val timeMillis = measureTimeMillis {
            val result = async { doBigMath() }
            val result2 = async { doBigMath() }
            val result3 = async { doBigMath() }
            val result4 = async { doBigMath() }
            val result5 = async { doBigMath() }
            val result6 = async { doBigMath() }

            println(result.await())
            println(result2.await())
            println(result3.await())
            println(result4.await())
            println(result5.await())
            println(result6.await())
        }
        println("took $timeMillis")
    }

    @Test
    fun testLaunch() = runBlocking {
        val timeMillis = measureTimeMillis {
            // Pareleliza
            val result = launch { doBigMath() }
            val result2 = launch { doBigMath() }
            val result3 = launch { doBigMath() }
            val result4 = launch { doBigMath() }
            val result5 = launch { doBigMath() }
            val result6 = launch { doBigMath() }

            // Como o lança não retorna nada, então terei Unit
            // Ele sabe que terminou, mas não sabe o retorno.
            println(result.join())
            println(result2.join())
            println(result3.join())
            println(result4.join())
            println(result5.join())
            println(result6.join())
        }
        println("took $timeMillis")
    }

    private suspend fun doBigMath() = withContext(Dispatchers.Default) {
        println(Thread.currentThread())
        var result = BigDecimal(Random.nextDouble())

        repeat(10000) {
            result = result.multiply(BigDecimal(Random.nextDouble()))
        }
        result
    }
}
package com.androidbootcamp.kotlincoroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class JobExampleTest {

    @Test
    fun joinExample() = runBlocking {
        println("before ${Thread.currentThread()}")

        val job = launch(Dispatchers.Default) {
            println("inside ${Thread.currentThread()}")
            launch(CoroutineName("Teste Inside 2")) {
                println("inside 2 ${Thread.currentThread()}")
            }
        }

        // Irei esperar o launch terminar
        job.join()

        println("after ${Thread.currentThread()}")
    }

    @Test
    fun cancelExample() = runBlocking {
        println("before ${Thread.currentThread()}")

        val job = launch {
            println("inside ${Thread.currentThread()}")
        }

        // O inside nem é executado, pois o job foi cancelado!
        job.cancel()

        println("after ${Thread.currentThread()}")
    }

    @Test
    fun cancelParentJobExample() = runBlocking {
        println("before ${Thread.currentThread()}")

        // Mãe
        val job = launch {
            // Filha
            launch {
                println("inside child job ${Thread.currentThread()}")
            }
            println("inside parent job ${Thread.currentThread()}")
        }

        // Cancelo o pai e consequentemente as filhas são canceladas.
        job.cancel()

        println("after ${Thread.currentThread()}")
    }

    @Test
    fun cancelChildJobExample() = runBlocking {
        println("before ${Thread.currentThread()}")

        launch {
            // Filha
            val childJob = launch {
                println("inside child job ${Thread.currentThread()}")
            }
            childJob.cancel() // Cancelo a filha
            println("inside parent job ${Thread.currentThread()}") // Mãe
        }

        println("after ${Thread.currentThread()}")
    }
}
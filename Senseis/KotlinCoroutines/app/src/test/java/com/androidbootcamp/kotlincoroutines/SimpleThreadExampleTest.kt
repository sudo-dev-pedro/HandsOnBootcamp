package com.androidbootcamp.kotlincoroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SimpleThreadExampleTest {
    @Test
    fun threadExample() {
        println("before ${Thread.currentThread()}")

        Thread {
            println("inside ${Thread.currentThread()}")
        }.also {
            it.start()
        }

        println("after ${Thread.currentThread()}")
    }

    @Test
    fun coroutineExample() = runBlocking {
        println("before ${Thread.currentThread()}")

        launch {
            println("inside ${Thread.currentThread()}")
        }

        println("after ${Thread.currentThread()}")
    }
}
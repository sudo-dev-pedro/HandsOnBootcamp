package com.androidbootcamp.kotlincoroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

class ContextExampleTest {
    @Test
    fun withContextExample() = runBlocking {
        println("before ${Thread.currentThread()}")

        launch {
            println("inside ${Thread.currentThread()}")
            withContext(Dispatchers.IO) {
                println("inside io ${Thread.currentThread()}")
            }
        }

        println("after ${Thread.currentThread()}")
    }

    @Test
    fun withContextMultipleModifier() = runBlocking {
        println("before ${Thread.currentThread()}")

        launch(Dispatchers.Default + CoroutineName("my-parent-coroutine")) {
            println("inside ${Thread.currentThread()}")
            withContext(Dispatchers.IO) {
                println("inside io ${Thread.currentThread()}")
            }

            withContext(CoroutineName("my-custom-name")) {
                println("inside custom name ${Thread.currentThread()}")
            }
        }

        println("after ${Thread.currentThread()}")
    }
}
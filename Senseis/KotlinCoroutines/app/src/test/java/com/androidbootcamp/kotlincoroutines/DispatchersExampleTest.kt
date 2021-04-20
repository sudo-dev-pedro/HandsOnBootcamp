package com.androidbootcamp.kotlincoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

class DispatchersExampleTest {
    @Test
    fun defaultDispatcher() = runBlocking {
        println("before: ${Thread.currentThread()}")
        withContext(Dispatchers.Default) {
            println("inside: ${Thread.currentThread()}")
        }
        println("after: ${Thread.currentThread()}")
    }

    @Test
    fun defaultDispatcherPool() = runBlocking {
        println("before: ${Thread.currentThread()}")
        repeat(64) {
            launch(Dispatchers.Default) {
                Thread.sleep(10)
                println("inside: ${Thread.currentThread()}")
            }
        }
        delay(20)
        println("after: ${Thread.currentThread()}")
    }

    @Test
    fun ioDispatcher() = runBlocking {
        println("before: ${Thread.currentThread()}")
        withContext(Dispatchers.IO) {
            println("inside: ${Thread.currentThread()}")
        }
        println("after: ${Thread.currentThread()}")
    }

    @Test
    fun ioDispatcherPool() = runBlocking {
        println("before: ${Thread.currentThread()}")
        repeat(64) {
            launch(Dispatchers.IO) {
                Thread.sleep(10)
                println("inside: ${Thread.currentThread()}")
            }
        }
        delay(20)
        println("after: ${Thread.currentThread()}")
    }

    @Test
    fun uncofinedDispatcher() = runBlocking(Dispatchers.Unconfined) {
        println("before: ${Thread.currentThread()}")
        withContext(Dispatchers.Default) {
            println("inside: ${Thread.currentThread()}")
        }
        println("after: ${Thread.currentThread()}")
    }
}
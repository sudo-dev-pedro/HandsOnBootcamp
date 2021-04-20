package com.example.designpatterns

import com.example.designpatterns.patterns.creational.singleton.Singleton
import com.example.designpatterns.patterns.creational.singleton.SingletonCompanion
import com.example.designpatterns.patterns.creational.singleton.SingletonJava
import com.example.designpatterns.patterns.creational.singleton.SingletonKotlin
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SingletonTest {

    @Test
    fun `should create the same object SingletonJava`() {
        val singletonJava = SingletonJava.getInstance()
        val singletonJava2 = SingletonJava.getInstance()

        assertEquals(singletonJava, singletonJava2)
    }

    @Test
    fun `should create the same object SingletonJava even on different thread`() {
        var singletonJava: SingletonJava? = null
        var singletonJava2: SingletonJava? = null

        Thread(Runnable {
            singletonJava = SingletonJava.getInstance()
        }).start()

        Thread(Runnable {
            singletonJava2 = SingletonJava.getInstance()
        }).start()

        Thread.sleep(3000)

        assertNotNull(singletonJava)
        assertNotNull(singletonJava2)

        assertEquals(singletonJava, singletonJava2)
    }

    @Test
    fun `should create the same object SingletonCompanion even on different thread`() {
        var singletonKotlin1: SingletonCompanion? = null
        var singletonKotlin2: SingletonCompanion? = null

        Thread(Runnable {
            singletonKotlin1 = SingletonCompanion.getInstance("")
        }).start()

        Thread(Runnable {
            singletonKotlin2 = SingletonCompanion.getInstance("")
        }).start()

        Thread.sleep(3000)

        assertNotNull(singletonKotlin1)
        assertNotNull(singletonKotlin2)

        assertEquals(singletonKotlin1, singletonKotlin2)
    }

    @Test
    fun `should create the same object Singleton even on different thread`() {
        var singletonKotlin1: Singleton? = null
        var singletonKotlin2: Singleton? = null

        Thread(Runnable {
            singletonKotlin1 = Singleton
        }).start()

        Thread(Runnable {
            singletonKotlin2 = Singleton
        }).start()

        Thread.sleep(3000)

        assertNotNull(singletonKotlin1)
        assertNotNull(singletonKotlin2)

        assertEquals(singletonKotlin1, singletonKotlin2)
    }
}
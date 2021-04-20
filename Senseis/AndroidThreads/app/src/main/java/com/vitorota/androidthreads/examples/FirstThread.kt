package com.vitorota.androidthreads.examples

fun main() {
    println("This is from the default thread")

    val newThread = Thread {
        println("This is from my newThread")
    }

    val customThread = CustomThread()

    newThread.start()
    customThread.start()
}

private class CustomThread : Thread() {
    override fun run() {
        println("This is from my custom Thread")
    }
}
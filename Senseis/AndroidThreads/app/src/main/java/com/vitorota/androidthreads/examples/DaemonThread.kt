package com.vitorota.androidthreads.examples

fun main() {
    //the main thread is a non-daemon thread
    println("starting program")

    val thread = createNonDaemonThread()
//    val thread = createDaemonThread() //the first print from this can be called or not, depends on jvm
    thread.start()

    println("finishing program")
}

fun createNonDaemonThread(): Thread {
    val thread = Thread {
        repeat(5) {
            println("non daemon thread [$it]")
            Thread.sleep(1_000)
        }
    }
    //this is the default for new threads
    thread.isDaemon = false
    return thread
}

fun createDaemonThread(): Thread {
    val thread = Thread {
        repeat(5) {
            println("daemon thread [$it]")
            Thread.sleep(1_000)
        }
    }
    thread.isDaemon = true
    return thread
}
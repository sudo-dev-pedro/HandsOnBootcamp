package br.com.raywenderlich.threads

class SimpleThread : Thread() {
    override fun run() {
//        println("${Thread.currentThread()} está ativa.")
    }
}

class SimpleRunnable : Runnable {
    override fun run() {
//        println("${Thread.currentThread()} está ativa (Runnable).")
    }
}

fun main() {
    val simpleThread = SimpleThread()
    val simpleRunnable = Thread(SimpleRunnable())

    val newThread = Thread {
        println("Essa é minha nova Thread")
    }

    println("Essa é a Thread principal! (Main)")

//    simpleThread.start()
//    simpleRunnable.start()
    newThread.start()
}
package com.example.designpatterns.patterns.creational.singleton

//Veja os testes unitários
// A keyword 'object' permite a criação de um Singleton de modo seguro para Thread
object Singleton {
    init {
        Thread.sleep(1000)
    }
}

object SingletonKotlin {
    private var number = 0
    fun getInstanceNumber(): Int {
        return ++number
    }
}

/*
Caso seja necessário a passagem de parametro pelo construtor
podemos construir um Singleton utilizando um 'companion object' para termos a
nossa referencia estática da classe, mantendo o construtor privado.

É necessário adicionar 'synchronized' caso trabalhe com multithreading
*/
class SingletonCompanion private constructor(var parameter: String) {
    /*
      Este trecho do código foi adicionado apenas para simular o comportamento de criação em
      diferentes Threads.
    */
    init {
        Thread.sleep(1000)
    }

    companion object {
        @Volatile
        private var INSTANCE: SingletonCompanion? = null

        fun getInstance(parameter: String) = synchronized(this) {
            INSTANCE ?: SingletonCompanion(parameter).also { singleton ->
                INSTANCE = singleton
            }
        }
    }
}
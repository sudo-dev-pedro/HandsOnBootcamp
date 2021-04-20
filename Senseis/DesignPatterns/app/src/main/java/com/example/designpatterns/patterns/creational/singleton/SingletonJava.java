package com.example.designpatterns.patterns.creational.singleton;

//Veja os testes unitários

public class SingletonJava {
    //Requer um construtor privado
    private SingletonJava() {
        /*
        Este trecho do código foi adicionado apenas para simular o comportamento de criação em
        diferentes Threads.
        */
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    //Requer a referência estática
    private static SingletonJava instance = null;

    // Método estático para retornar a Intância da classe já que o padrão
    // não permite a criação atráves do seu construtor de forma direta.
    public static SingletonJava getInstance() {
        /*
         Caso o Singleton seja usado em uma aplicação onde há multithreading
         devemos garantir a chamada apenas uma vez por Thread
         A keyword synchronized garante que o bloco seja executado em apenas em uma Thread
         */
        synchronized (SingletonJava.class) {
            if (instance == null) {
                instance = new SingletonJava();
            }
        }
        return instance;
    }
}


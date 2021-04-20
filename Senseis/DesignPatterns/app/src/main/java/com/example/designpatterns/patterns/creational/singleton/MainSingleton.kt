package com.example.designpatterns.patterns.creational.singleton

fun main() {
    val singletonJava = SingletonJava.getInstance()
    val singletonJava2 = SingletonJava.getInstance()

    println("Objeto Java 1: ${singletonJava.hashCode()}")
    println("Objeto Java 2: ${singletonJava2.hashCode()}")

    val singletonCompanion = SingletonCompanion.getInstance("Android")
    val singletonCompanion2 = SingletonCompanion.getInstance("iOS")

    println("Objeto Kotlin with Parameter 1: ${singletonCompanion.parameter}")
    println("Objeto Kotlin with Parameter 2: ${singletonCompanion2.parameter}")

    val singletonKotlin = SingletonKotlin
    val singletonKotlin2 = SingletonKotlin

    println("Objeto Kotlin 1: ${singletonKotlin.hashCode()}")
    println("Objeto Kotlin 2: ${singletonKotlin2.hashCode()}")

    //Retornando o valor incrementado a cada chamada do objeto
    println(SingletonKotlin.getInstanceNumber())
    println(SingletonKotlin.getInstanceNumber())
    println(SingletonKotlin.getInstanceNumber())
    println(SingletonKotlin.getInstanceNumber())
}
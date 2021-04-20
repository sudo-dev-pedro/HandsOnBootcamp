package com.example.designpatterns.patterns.behavior

//Strategy
interface Operation {
    fun operate(number1: Int, number2: Int): Int
}

//ConcreteStrategy
class Sum : Operation {
    override fun operate(number1: Int, number2: Int): Int {
        return number1 + number2
    }
}

//ConcreteStrategy
class Sub : Operation {
    override fun operate(number1: Int, number2: Int): Int {
        return number1 - number2
    }
}

//Exemplo com uso de High Order Function
//Context
class Calculator(
    private val operation: (Int, Int) -> Int
) {
    fun calculate(number1: Int, number2: Int) {
        println(operation(number1, number2))
    }
}

//Exemplo com uso de interface
//Context
/*
class Calculator(private val operation: Operation) {
    fun calculate(number1: Int, number2: Int) {
        println(operation.operate(number1, number2))
    }
}*/

fun main() {

    // val calculator = Calculator(Sum())

    /*
    val sum: (Int, Int) -> Int = { n1, n2 ->
        n1 + n2
    }
    val calculator = Calculator(sum)
    */

    val calculator = Calculator { n1, n2 ->
        n1 / n2
    }

    calculator.calculate(6, 2)
}
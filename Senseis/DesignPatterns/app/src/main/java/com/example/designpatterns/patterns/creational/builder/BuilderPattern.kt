package com.example.designpatterns.patterns.creational.builder

/*
Construção de uma classe com o Design Pattern Builder
*/
class Order private constructor(
    private var drink: String,
    private var meal: String,
    private var dessert: String
) {
    class Builder(
        var drink: String = "NOTHING",
        var meal: String = "NOTHING",
        var dessert: String = "NOTHING"
    ) {

        fun drink(drink: String) = apply {
            this.drink = drink
            return this
        }

        fun meal(meal: String) = apply {
            this.meal = meal
            return this
        }

        fun dessert(dessert: String) = apply {
            this.dessert = dessert
            return this
        }

        fun create(): Order {
            return Order(
                drink = this.drink,
                meal = this.meal,
                dessert = this.dessert
            )
        }
    }

    override fun toString(): String {
        return "\n\nOrder ${this.hashCode()}" +
                "\ndrink: $drink" +
                "\nmeal: $meal" +
                "\ndessert: $dessert"
    }
}

/*
Com o recurso de Parâmetros Nomeados podemos ter a mesma aplicação do
Pattern Builder, definindo valores default e construindo o Objeto de acordo com
a necessidade
*/
data class OrderKotlin(
    var drink: String = "NOTHING",
    var meal: String = "NOTHING",
    var dessert: String = "NOTHING"
) {
    override fun toString(): String {
        return "\n\nOrder ${this.hashCode()}" +
                "\ndrink: $drink" +
                "\nmeal: $meal" +
                "\ndessert: $dessert"
    }
}

fun main() {
    val order1 = Order.Builder()
        .meal("Pure de batatas")
        .dessert("Pudim de Leite")
        .drink("Agua")
        .create()

    val order2 = Order.Builder()
        .dessert("Sorvete")
        .create()


    println(order1)
    println(order2)

    val orderKotlin1 = OrderKotlin(
        drink = "Coca Cola",
        meal = "Tropeiro",
        dessert = "Chocolate"
    )

    val orderKotlin2 = OrderKotlin(
        meal = "Lasanha"
    )

    println(orderKotlin1)
    println(orderKotlin2)
}
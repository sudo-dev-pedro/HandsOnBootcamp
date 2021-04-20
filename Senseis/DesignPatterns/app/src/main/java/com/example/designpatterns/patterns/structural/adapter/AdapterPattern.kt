package com.example.designpatterns.patterns.structural.adapter

/*
Exemplo da aplicação do Design Pattern Adapter.
Problema: Tomada de 2 pinos recebe apenas Plug de 2 pinos, ao ter um novo plug(Plug de 3 pinos)
devemos utilizar a mesma tomada sem alterar o atual.
*/

//TARGET
interface TwoPlug {
    fun connectTwoPlug(): String
}

class TwoPlugImpl : TwoPlug {
    override fun connectTwoPlug(): String {
        return "Plug de 2 pinos foi plugado -> "
    }
}

//CLIENT
// Não deve sofrer alteração
class OutletTwoPin(private val plug: TwoPlug) {
    fun turnOn() {
        println("${plug.connectTwoPlug()}tomada de 2 pinos.")
    }
}

//ADAPTEE
interface ThreePlug {
    fun connectThreePlug(): String
}

class ThreePlugImpl : ThreePlug {
    override fun connectThreePlug(): String {
        return "Plug de 3 pinos foi plugado -> "
    }
}

//ADAPTER
class OutletAdapter(private val threePlug: ThreePlug) : TwoPlug {
    override fun connectTwoPlug(): String {
        return threePlug.connectThreePlug()
    }
}

fun main() {
    val twoPlug = TwoPlugImpl()
    val threePlug = ThreePlugImpl()

    val outletTwoPin1 = OutletTwoPin(twoPlug)
    val outletTwoPin2 = OutletTwoPin(OutletAdapter(threePlug))

    outletTwoPin1.turnOn()
    outletTwoPin2.turnOn()
}
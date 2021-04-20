package com.example.testapplication

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TrafficSystem : KoinComponent {

    fun driveCar(direction: Direction) {
        car3.drive(direction)
    }

    fun driveToHome() {
        car1.drive(Direction.WEST, 80)
        car1.drive(Direction.EAST, 120)
        car1.drive(Direction.SOUTH, 130)
    }

    // Realizando a injeção dos meus Carros
    val car1: Car by inject()
    val car2: Car by inject()

    // Essa informa pode ser abandonada
    private var car3: Car = Car(Engine())
}
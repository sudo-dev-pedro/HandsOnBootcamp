package com.example.testapplication

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TrafficSystemInjection : KoinComponent {

    fun driveCar(direction: Direction) {
        car3.drive(direction)
    }

    fun driveToHome() {
        car1.drive(Direction.WEST, 80)
        car1.drive(Direction.EAST, 120)
        car1.drive(Direction.SOUTH, 130)
    }

    val car1: Car by inject()
    val car2: Car by inject()
    private val car3: Car by inject()
}
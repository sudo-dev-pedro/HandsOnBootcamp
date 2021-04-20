package com.example.testapplication

class TrafficSystem {
    fun driveCar(direction: Direction) {
        car3.drive(direction)
    }

    fun driveToHome() {
        car1.drive(Direction.WEST, 80)
        car1.drive(Direction.EAST, 120)
        car1.drive(Direction.SOUTH, 130)
    }

    lateinit var car1: Car
    lateinit var car2: Car
    private var car3: Car = Car()
}
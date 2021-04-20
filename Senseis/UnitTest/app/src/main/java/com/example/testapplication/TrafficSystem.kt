package com.example.testapplication

class TrafficSystem() {
    constructor(
            _car1: Car,
            _car2: Car,
            _car3: Car
    ) : this() {
        car1 = _car1
        car2 = _car2
        car3 = _car3
    }

    fun driveCar(direction: Direction) {
        car3.drive(direction)
    }

    fun driveHome() {
        car1.drive(Direction.WEST, 80)
        car1.drive(Direction.EAST, 120)
        car1.drive(Direction.SOUTH, 130)
    }

    lateinit var car1: Car
    lateinit var car2: Car
    private var car3: Car = Car()
}
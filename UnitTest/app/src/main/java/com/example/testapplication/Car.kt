package com.example.testapplication

import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import com.example.testapplication.State
import java.lang.Exception

class Car() {
    constructor(state: State) : this() {
        _state = state
    }

    var engine: Engine = Engine()
    var _state = State.STOPPED
    var _passengers = 0
    val totalPassengers = 5
    val doors = listOf(
        Door(DoorType.FRONT_LEFT), Door(DoorType.FRONT_RIGHT),
        Door(DoorType.REAR_LEFT), Door(DoorType.REAR_RIGHT)
    )
    var _speed = 0

    fun drive(direction: Direction, speed: Int = 0): Outcome {
        if (_state == State.STOPPED) {
            _state = State.MOVING
            _speed = speed
            return Outcome.OK
        }
        return Outcome.FAILURE
    }

    fun enterPassenger(amount: Int): Unit {
        if (totalPassengers > _passengers + amount) {
            _passengers += amount
            return
        }

        throw Exception("Not possible to add $amount more passenger(s).")
    }

    fun door(type: DoorType): Door {
        return doors.first { it.type == type }
    }
}

class Engine() {
    fun start(): Boolean {
        return true
    }
}
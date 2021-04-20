package com.example.testapplication

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.Exception

// Passando o Engine como parâmetro
class Car(
    private val engine: Engine
) : KoinComponent {

    constructor(state: State) : this(Engine()) {
        _state = state
    }

    private var _state = State.STOPPED
    private var _passengers = 0
    private val totalPassengers = 5
    private var _speed = 0
    private val doors = listOf(
        Door(DoorType.FRONT_LEFT), Door(DoorType.FRONT_RIGHT),
        Door(DoorType.REAR_LEFT), Door(DoorType.REAR_RIGHT)
    )

    fun drive(direction: Direction, speed: Int = 0): Outcome {
        if (_state == State.STOPPED) {
            _state = State.MOVING
            _speed = speed
            return Outcome.OK
        }
        return Outcome.FAILURE
    }

    fun enterPassenger(amount: Int) {
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
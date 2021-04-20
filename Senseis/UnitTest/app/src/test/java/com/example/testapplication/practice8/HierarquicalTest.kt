package com.example.testapplication.practice8

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.DoorType
import com.example.testapplication.Outcome
import com.example.testapplication.WindowState
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class HierarquicalTest {
    @Test
    fun `should validate hierarquical mock`() {
        val car = mockk<Car>() {
            every { doors } returns listOf(
                mockk {
                    every { type } returns DoorType.REAR_RIGHT
                    every { windowState() } returns WindowState.DOWN
                },
                mockk {
                    every { type } returns DoorType.FRONT_LEFT
                    every { windowState() } returns WindowState.UP
                })
            every { totalPassengers } returns 3
            every { drive(Direction.NORTH) } returns Outcome.OK
        }

        assertEquals(DoorType.REAR_RIGHT, car.doors.first().type)
        assertEquals(WindowState.UP, car.doors.last().windowState())

        assertEquals(3, car.totalPassengers)

        assertEquals(Outcome.OK, car.drive(Direction.NORTH))
    }
}
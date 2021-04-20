package com.example.testapplication.practice7

import com.example.testapplication.Car
import com.example.testapplication.Door
import com.example.testapplication.DoorType
import com.example.testapplication.WindowState
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ChainedCalls {
    @Test
    fun `should validate chained calls`() {
        val car = mockk<Car>()

        every { car.door(DoorType.FRONT_LEFT).windowState() } returns WindowState.DOWN

        val door = car.door(DoorType.FRONT_LEFT) // returns chained mock for Door
        car.door(DoorType.FRONT_LEFT).windowState() // returns WindowState.UP

        assertEquals(WindowState.DOWN, door.windowState())
        assertEquals(WindowState.DOWN, car.door(DoorType.FRONT_LEFT).windowState())

        verify(exactly = 3) { car.door(DoorType.FRONT_LEFT).windowState() }
    }

    @Test
    fun `should not be able to validate door on chained call`() {
        val car = mockk<Car>()

        every { car.door(DoorType.FRONT_LEFT).windowState() } returns WindowState.DOWN

        val door = car.door(DoorType.FRONT_LEFT) // returns chained mock for Door
        car.door(DoorType.FRONT_LEFT).windowState() // returns WindowState.UP

        assertEquals(DoorType.FRONT_LEFT, door.type)

        verify { car.door(DoorType.FRONT_LEFT).windowState() }
    }
}
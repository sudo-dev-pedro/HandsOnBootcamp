package com.example.testapplication.practice

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class SetupTest {
    @Test
    fun `simplest case`() {
    }

    @Test
    fun simplestCase() {
    }

    @Test
    fun simplest_case() {
    }

    @Test
    fun `when I drive a car should return OK`() {
    }

    @Test
    fun whenIDriveACar_shouldReturnOK() {
        val car = mockk<Car>()
        val car2 = Car()

        every { car.drive(any()) } returns Outcome.OK

        var response1 = car.drive(Direction.NORTH)
        var response2 = car.drive(Direction.SOUTH)

        assertEquals(Outcome.OK, response1)
        assertEquals(Outcome.OK, response2)

        verify { car.drive(any()) }

        confirmVerified(car)
    }

    @MockK
    lateinit var car1: Car

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `annotation case`() {
        every { car1.drive(Direction.NORTH) } returns Outcome.FAILURE
        val response = car1.drive(Direction.NORTH)

        assertEquals(Outcome.FAILURE, response)
    }

    @Test
    fun `when driveNorth should drive north`() {
        every { car1.drive(any()) } returns Outcome.FAILURE

        car1.driveNorth()
    }
}
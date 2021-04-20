package com.example.testapplication.practice6

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import io.mockk.Called
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ArgumentTest {
    @MockK
    lateinit var car: Car

    @Test
    fun `should match arguments directly`() {
        every { car.drive(or(Direction.NORTH, Direction.EAST)) } returns Outcome.OK
        every { car.drive(Direction.SOUTH, less(20)) } returns Outcome.FAILURE
        every { car.action(match { it.startsWith("CUIDADO") }) } just Runs

        car.drive(Direction.NORTH)
        car.drive(Direction.EAST)
        car.action("CUIDADO COM O PEDESTRE")
        car.action("CUIDADO COM O CICLISTA")

        assertEquals(Outcome.FAILURE, car.drive(Direction.SOUTH, 10))

        verify(exactly = 3) {
            car.drive(
                or(or(Direction.NORTH, Direction.EAST), Direction.SOUTH),
                any()
            )
        }

        verify(exactly = 0) {
            car.drive(Direction.WEST, any())
        }

        verify {
            car.action(match { it.startsWith("CUIDADO") })
            car.drive(Direction.NORTH)
            car.drive(Direction.WEST, any()) wasNot Called
        }
    }

    @Test
    fun `should match any argument`() {
        every { car.drive(any()) } returns Outcome.OK

        car.drive(Direction.NORTH)
        car.drive(Direction.SOUTH)

        verify { car.drive(any()) }
    }

    @Test
    fun `should throw error when parameter is not correct`() {
        every { car.drive(Direction.NORTH) } returns Outcome.OK

        assertEquals(Outcome.OK, car.drive(Direction.SOUTH))
    }
}
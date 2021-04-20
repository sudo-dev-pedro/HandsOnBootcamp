package com.example.testapplication.practice10

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import io.mockk.*
import org.junit.Test

class VerificationTest {

    @Test
    fun `should verify calls`() {
        val car = mockk<Car>()

        every { car.drive(any(), any()) } returns Outcome.OK

        car.drive(Direction.NORTH)
        car.drive(Direction.EAST, 50)
        car.drive(Direction.SOUTH, 30)
        car.drive(Direction.WEST, 100)

        verify(exactly = 4) {
            car.drive(any(), any())
        }

        verify(exactly = 0) {
            car.drive(Direction.SOUTH)
        }

        verify(atLeast = 1) {
            car.drive(or(Direction.EAST, Direction.SOUTH), any())
        }

        verify(atMost = 3) {
            car.drive(any(), more(0))
        }

        verify(exactly = 1) {
            car.drive(any())
        }
    }

    @Test
    fun `should verify call order`() {
        val car = mockk<Car>()

        every { car.drive(any(), any()) } returns Outcome.OK

        car.drive(Direction.NORTH)
        car.drive(Direction.EAST, 50)
        car.drive(Direction.SOUTH, 30)

        // Verifica se uma correu atrás da outra
        verifySequence {
            car.drive(Direction.NORTH)
            car.drive(Direction.EAST, 50)
            car.drive(Direction.SOUTH, 30)
        }

        // Verificação da ordem das chamadas
        verifyOrder {
            car.drive(Direction.NORTH)
            car.drive(Direction.SOUTH, any())
        }

        verify { car.drive(Direction.WEST) wasNot Called }

    }

    @Test
    fun `should confirm verification`() {
        val car = mockk<Car>()

        every { car.drive(Direction.NORTH) } returns Outcome.OK
        every { car.drive(or(Direction.SOUTH, Direction.WEST)) } returns Outcome.FAILURE

        car.drive(Direction.NORTH)
        car.drive(Direction.SOUTH)
        car.drive(Direction.WEST)

        verifyOrder {
            car.drive(Direction.NORTH)
            car.drive(Direction.SOUTH)
            car.drive(Direction.WEST)
        }

        confirmVerified(car)
    }

    @Test
    fun `should exclude confirmation for specific call`() {
        val car = mockk<Car>()

        every { car.drive(Direction.NORTH) } returns Outcome.OK
        every { car.drive(or(Direction.SOUTH, Direction.WEST)) } returns Outcome.FAILURE

        excludeRecords {
            car.drive(Direction.SOUTH)
        }

        car.drive(Direction.NORTH)
        car.drive(Direction.SOUTH)
        car.drive(Direction.WEST)

        verifyOrder {
            car.drive(Direction.NORTH)
            car.drive(Direction.WEST)
        }

        confirmVerified(car)
    }
}
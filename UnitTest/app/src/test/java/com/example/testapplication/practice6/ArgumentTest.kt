package com.example.testapplication.practice6

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
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
        every { car.drive(or(Direction.NORTH, Direction.WEST)) } returns Outcome.OK
        every { car.drive(Direction.WEST, more(20)) } returns Outcome.FAILURE

        car.drive(Direction.NORTH)
        car.drive(Direction.SOUTH)

        assertEquals(Outcome.FAILURE, car.drive(Direction.WEST, 20))

        verify(exactly = 3) {
            car.drive(
                or(
                    or(
                        Direction.NORTH, Direction.SOUTH
                    ), Direction.WEST
                ), any()
            )
        }

        verify {
            car.drive(Direction.EAST) wasNot Called
        }
    }
}
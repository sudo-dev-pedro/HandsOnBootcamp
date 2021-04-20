package com.example.testapplication.practice3

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import junit.framework.TestCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MockExtensionTest {
    @MockK
    lateinit var car1: Car

    @RelaxedMockK
    lateinit var car2: Car

    @Test
    fun `should work with mock extension`() {
        every { car1.drive(any()) } returns Outcome.OK

        car1.drive(Direction.SOUTH)
        car2.drive(Direction.NORTH)

        verify {
            car1.drive(Direction.SOUTH)
            car2.drive(Direction.NORTH)
        }
    }

    @Test
    fun `when car is stopped and drive should move`(@MockK car2: Car) {
        every { car2.drive(Direction.NORTH) } returns Outcome.OK

        car2.drive(Direction.NORTH)

        TestCase.assertTrue(true)
    }

    @Test
    fun `when relaxed car is stopped and drive should move`(@RelaxedMockK relaxedCar2: Car) {
        relaxedCar2.drive(Direction.NORTH)

        TestCase.assertTrue(true)
    }

    @Test
    fun `when testing both cars should move`(@MockK car3: Car) {
        every { car1.drive(Direction.SOUTH) } returns Outcome.FAILURE
        every { car3.drive(Direction.WEST) } returns Outcome.OK

        car1.drive(Direction.SOUTH)
        car3.drive(Direction.WEST)

        verify {
            car1.drive(Direction.SOUTH)
            car3.drive(Direction.WEST)
        }
    }
}
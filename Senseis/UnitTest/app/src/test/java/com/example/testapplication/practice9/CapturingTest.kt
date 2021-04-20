package com.example.testapplication.practice9

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import com.example.testapplication.TrafficSystem
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CapturingTest {

    @Test
    fun `should capture parameters`() {
        val car = mockk<Car>()

        val speedSlot = slot<Int>()
        val list = mutableListOf<Int>()

        every {
            car.drive(
                    Direction.NORTH,
                    speed = capture(speedSlot))
        } answers {
            println(speedSlot.captured)

            Outcome.RECORDED
        }

        every {
            car.drive(
                    speed = capture(list),
                    direction = Direction.SOUTH
            )
        } answers {
            println(list)

            Outcome.RECORDED
        }

        car.drive(speed = 15, direction = Direction.NORTH) // prints 15
        car.drive(speed = 16, direction = Direction.SOUTH) // prints 16

        verify(exactly = 2) {
            car.drive(speed = or(15, 16), direction = any())
        }
    }

    @MockK
    lateinit var car1: Car

    @InjectMockKs
    var trafficSystem = TrafficSystem()

    @Test
    fun `should capture traffic system parameters`() {
        val speedSlot = slot<Int>()
        val direction = slot<Direction>()

        every { car1.drive(capture(direction), any()) } returns Outcome.OK

        trafficSystem.driveHome()
    }
}
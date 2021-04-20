package com.example.testapplication.practice9

import com.example.testapplication.*
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class CapturingTest {

    @MockK
    private lateinit var car1: Car

    @RelaxedMockK
    lateinit var car2: Car

    @RelaxedMockK
    var car3: Car = Car(engine = Engine())

    @InjectMockKs(overrideValues = true)
    var trafficSystem = TrafficSystemInjection()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        trafficSystem = TrafficSystemInjection()
    }

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

    @Test
    fun `should capture traffic system parameters`() {
//        val speedSlot = slot<Int>()
        val direction = slot<Direction>()

        every { car1.drive(capture(direction), any()) } returns Outcome.OK

        trafficSystem.driveToHome()

        verify {
            trafficSystem.car1.drive(Direction.WEST, less(100))
            trafficSystem.car1.drive(Direction.EAST, more(100))
            trafficSystem.car1.drive(Direction.SOUTH, 130)
        }
    }
}
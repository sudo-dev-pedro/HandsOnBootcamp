package com.example.testapplication.practice2

import com.example.testapplication.*
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class AnotherInjectionTest {

    @MockK
    lateinit var car1: Car

    @RelaxedMockK
    lateinit var car2: Car

    @RelaxedMockK
    var car3: Car = Car()

    @InjectMockKs(overrideValues = true)
    var trafficSystem1 = TrafficSystemInjection()

    lateinit var trafficSystem2: TrafficSystemInjection

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        trafficSystem2 = TrafficSystemInjection(car1, car2, car3)
    }

    @Test
    fun `should be able to use all cars`() {
        every { car1.drive(any()) } returns Outcome.OK

        trafficSystem1.car1.drive(Direction.NORTH)
        trafficSystem1.car2.drive(Direction.SOUTH)
        trafficSystem1.driveCar(Direction.SOUTH)

        verify {
            car1.drive(Direction.NORTH)
            car2.drive(Direction.SOUTH)
            car3.drive(Direction.SOUTH)
        }
    }

    @Test
    fun `should be able to use all cars without injection`() {

        every { car1.drive(any()) } returns Outcome.OK

        trafficSystem2.car1.drive(Direction.NORTH)
        trafficSystem2.car2.drive(Direction.SOUTH)
        trafficSystem2.driveCar(Direction.SOUTH)

        verify {
            car1.drive(Direction.NORTH)
            car2.drive(Direction.SOUTH)
            car3.drive(Direction.SOUTH)
        }
    }
}
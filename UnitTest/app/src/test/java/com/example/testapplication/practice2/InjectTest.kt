package com.example.testapplication.practice2

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import com.example.testapplication.TrafficSystem
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class InjectTest {

    @MockK
    private lateinit var car1: Car

    @RelaxedMockK
    private lateinit var car2: Car

    @RelaxedMockK
    private var car3 = Car()

    // OverrideValues = Faça a injeção mesmo que seja privado.
    @InjectMockKs(overrideValues = true)
    var trafficSystem = TrafficSystem()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should be able to use all cars`() {
        every { car1.drive(any()) } returns Outcome.OK

        trafficSystem.car1.drive(Direction.NORTH)
        trafficSystem.car2.drive(Direction.WEST)
        // Usando o car3
        // Caso o meu car 3 não seja RelaxedMockK isso aqui quebra!
        trafficSystem.driveCar(Direction.NORTH)

        excludeRecords {
            trafficSystem.driveCar(Direction.NORTH)
        }

        verifyAll {
//            car1.drive(Direction.NORTH)
            car2.drive(Direction.WEST)
//            car3.drive(Direction.NORTH)
        }

        verifyOrder {
//            car1.drive(Direction.NORTH)
            car2.drive(Direction.WEST)
//            car3.drive(Direction.NORTH)
        }
    }

}
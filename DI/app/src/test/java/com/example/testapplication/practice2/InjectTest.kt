package com.example.testapplication.practice2

import com.example.testapplication.*
import com.example.testapplication.di.appModule
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class InjectTest : KoinTest {

//    @MockK
//    private lateinit var car1: Car
//
//    @RelaxedMockK
//    private lateinit var car2: Car
//
//    @RelaxedMockK
//    private var car3 = Car(Engine())
//
//    // OverrideValues = Faça a injeção mesmo que seja privado.
//    @InjectMockKs(overrideValues = true)
//    var trafficSystem = TrafficSystem()

    @MockK
    private lateinit var trafficSystem: TrafficSystem

    private val car1: Car by inject()
    private val car2: Car by inject()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should be able to use all cars`() {

        startKoin {
            module { appModule }
        }

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

    @After
    fun tearDown() {
        stopKoin()
    }

}
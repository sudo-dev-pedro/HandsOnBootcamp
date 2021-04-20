package com.example.testapplication.practice4

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Engine
import com.example.testapplication.Outcome
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SpyTest {
    // Serve para misturar o mock com um objeto real
    // O SpyK irá encapsular um objeto real!
    // Ele irá continuar se comportando como um objeto real.
    @SpyK
    @InjectMockKs(overrideValues = true)
    var car2 = Car()

    @MockK
    lateinit var car1: Car

    @RelaxedMockK
    lateinit var engine: Engine

    var car = Car()

    @Test
    fun `should use mockK car`() {
        every { car1.drive(Direction.NORTH) } returns Outcome.OK

        car1.drive(Direction.NORTH)

        verify {
            car1.drive(Direction.NORTH)
        }
    }

    @Test
    fun `should verify annotated spy car`() {
        car2.drive(Direction.SOUTH)

        engine.start()

        /*
        O verify só funciona originalmente com um Mock, mas o Spy permite que eu faça isso com um objeto real.
        */
        verify {
            car2.drive(Direction.SOUTH)
        }
    }
}
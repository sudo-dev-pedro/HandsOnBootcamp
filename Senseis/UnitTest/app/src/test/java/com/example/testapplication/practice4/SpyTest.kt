package com.example.testapplication.practice4

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Engine
import com.example.testapplication.Outcome
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SpyTest {
    @SpyK
    @InjectMockKs(overrideValues = true)
    var spyCar = Car()

    @RelaxedMockK
    var engine: Engine = Engine()

    var car = Car()

    @Test
    fun `should verify spy`() {
        val car = spyk<Car>()
        car._engine = mockk(relaxed = true)

        car.drive(Direction.NORTH)

        verify {
            car.drive(Direction.NORTH)
            car._engine.start()
        }
    }

    @Test
    fun `should verify annotated spy`() {
        every { spyCar.drive(Direction.SOUTH) } returns Outcome.FAILURE

        var response = spyCar.drive(Direction.SOUTH)

        assertEquals(Outcome.FAILURE, response)

        verify {
            spyCar.drive(Direction.SOUTH)
            spyCar._engine
            spyCar._engine.start()
        }
    }

    @Test
    fun `should not verify object car`() {
        car._engine = Engine()
        car.drive(Direction.NORTH)

        verify { car.drive(Direction.NORTH) }
    }
}
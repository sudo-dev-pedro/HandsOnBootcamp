package com.example.testapplication.practice5

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Engine
import com.example.testapplication.MockObj
import com.example.testapplication.Outcome
import com.example.testapplication.State
import io.mockk.OfTypeMatcher
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.mockkConstructor
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.unmockkObject
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.After
import org.junit.Test

class MockingTests {

    @Test
    fun `should mock object`() {
        mockkObject(MockObj)

        assertEquals(3, MockObj.add(1, 2))

        every { MockObj.add(1, 2) } returns 55

        assertEquals(55, MockObj.add(1, 2))


        verify(exactly = 2) { MockObj.add(1, 2) }
    }

    @After
    fun tearDown() {
        unmockkObject(MockObj)
        // unmockkAll()
    }

    @Test
    fun `should throw error when not mocked`() {
        val car = mockkClass(Car::class)

        car.drive(Direction.NORTH)

        verify { car.drive(Direction.NORTH) }
    }

    @Test
    fun `should mock class`() {
        val car = mockkClass(Car::class)

        every { car.drive(Direction.NORTH) } returns Outcome.OK

        car.drive(Direction.NORTH)

        verify { car.drive(Direction.NORTH) }
    }

    @Test
    fun `should mock enum`() {
        assertEquals(200, Outcome.OK.value)

        mockkObject(Outcome.OK)

        every { Outcome.OK.value } returns 51

        assertEquals(51, Outcome.OK.value)
    }

    @Test
    fun `should mock constructor`() {
        mockkConstructor(Car::class)

        val car1 = Car()

        every { anyConstructed<Car>().drive(Direction.NORTH) } returns Outcome.FAILURE

        assertEquals(Outcome.FAILURE, car1.drive(Direction.NORTH))
        assertEquals(Outcome.OK, car1.drive(Direction.SOUTH))
    }

    @Test
    fun `should mock second constructor`() {
        mockkConstructor(Car::class)

        every { constructedWith<Car>().drive(Direction.NORTH) } returns Outcome.OK
        every { constructedWith<Car>(OfTypeMatcher<State>(State::class)).drive(Direction.NORTH) } returns Outcome.FAILURE
        every { constructedWith<Car>(OfTypeMatcher<Engine>(Engine::class)).drive(Direction.NORTH) } returns Outcome.RECORDED

        assertEquals(Outcome.OK, Car().drive(Direction.NORTH))
        assertEquals(Outcome.FAILURE, Car(State.STOPPED).drive(Direction.NORTH))
        assertEquals(Outcome.FAILURE, Car(Engine()).drive(Direction.NORTH))

        verify {
            constructedWith<Car>().drive(Direction.NORTH)
            constructedWith<Car>(OfTypeMatcher<State>(State::class)).drive(Direction.NORTH)
        }
    }
}
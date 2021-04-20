package com.example.testapplication.practice5

import com.example.testapplication.*
import io.mockk.OfTypeMatcher
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import io.mockk.mockkObject
import io.mockk.every
import io.mockk.verify
import io.mockk.mockkConstructor
import io.mockk.unmockkObject
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MockingTests {

    @Test
    fun `should mock object`() {
        mockkObject(MockObj)

        assertEquals(45, MockObj.add(23, 22))
    }


    // Sem ter o comportamento setado (every{}) temos a falha!
    @Test
    fun `should throw error when not mocked`() {
        val car = mockkClass(Car::class)

        car.drive(Direction.EAST)

        verify {
            car.drive(Direction.EAST)
        }
    }

    // Procede pois temos o compartamento (every{}) setado!
    @Test
    fun `should mocked class proceed`() {
        val car = mockkClass(Car::class)

        every { car.drive(Direction.EAST) } returns Outcome.OK

        car.drive(Direction.EAST)

        verify {
            car.drive(Direction.EAST)
        }
    }

    @Test
    fun `should mock enum`() {
        mockkObject(Outcome::class)

        every { Outcome.OK.value } returns 200

        val value = Outcome.OK.value

        assertEquals(200, value)
    }

    @Test
    fun `should primary mock constructor`() {
        mockkConstructor(Car::class)

        every { anyConstructed<Car>().drive(Direction.WEST) } returns Outcome.FAILURE

        val car1 = Car(Engine())

        assertEquals(Outcome.FAILURE, car1.drive(Direction.WEST))
        assertEquals(Outcome.OK, car1.drive(Direction.EAST))

        verify {
            car1.drive(Direction.WEST)
            car1.drive(Direction.EAST)
        }
    }

    /*
    Assim que é setada a direção, o state do carro
    muda e o Outcome fica como OK!
    */
    @Test
    fun `should mock second constructor`() {
        mockkConstructor(Car::class)

        // Sempre sete o(s) comportamento(s) antes!
        every { constructedWith<Car>().drive(Direction.NORTH) } returns Outcome.OK

        every {
            constructedWith<Car>(OfTypeMatcher<State>(State::class)).drive(Direction.NORTH)
        } returns Outcome.FAILURE

        assertEquals(Outcome.OK, Car(Engine()).drive(Direction.NORTH))
        assertEquals(Outcome.FAILURE, Car(State.STOPPED).drive(Direction.NORTH))

        verify(exactly = 1) {
//            Car().drive(Direction.NORTH)
            constructedWith<Car>().drive(Direction.NORTH)
        }

        verify(exactly = 1) {
//            Car(State.STOPPED).drive(Direction.NORTH)
            constructedWith<Car>(OfTypeMatcher<State>(State::class)).drive(Direction.NORTH)
        }

    }

    @After
    fun teardown() {
        unmockkObject(MockObj)
    }
}
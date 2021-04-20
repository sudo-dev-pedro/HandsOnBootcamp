package com.example.testapplication.practice1

import com.example.testapplication.Car
import com.example.testapplication.Direction
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MockTest {

    // Aqui nenhuma função tem comportamento! Por isso uso o every
    @MockK
    private lateinit var car1: Car

    @RelaxedMockK
    private lateinit var car2: Car

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when i call a mock car should not pass`() {
        car1.drive(Direction.EAST)
    }

    @Test
    fun `when call relaxed object should pass`() {
        car2.drive(Direction.WEST)
    }

}
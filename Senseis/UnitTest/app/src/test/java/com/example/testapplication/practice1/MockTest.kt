package com.example.testapplication.practice1

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Engine
import com.example.testapplication.Outcome
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.Boolean.TRUE

class MockTest {
    @MockK
    lateinit var car1: Car

    @RelaxedMockK
    lateinit var car2: Car

    @RelaxedMockK
    lateinit var engine: Engine

    @InjectMockKs
    lateinit var car3: Car

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when calling mock car should have error if not set`() {
        every { engine.start() } returns true

        car3.drive(Direction.NORTH)
    }

    @Test
    fun `when calling relaxed car should pass`() {
        val response = car2.drive(Direction.NORTH)
    }
}
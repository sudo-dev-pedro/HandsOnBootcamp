package com.example.testapplication.practice8

import com.example.testapplication.Car
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class HierarquicalTest {

    @MockK
    lateinit var car: Car

    @Test
    fun `should validate hierarquical mock`() {
        TODO()
    }

}
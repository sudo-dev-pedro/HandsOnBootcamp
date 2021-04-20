package com.example.testapplication.practice7

import com.example.testapplication.Car
import com.example.testapplication.DoorType
import com.example.testapplication.WindowState
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test

class ChainedCalls {

    @Test
    fun `should validate chained call`() {
        val car = mockk<Car>()

        // Lembre-se que aqui o Verify não passa, pois aqui se seta o comportamento.
        every { car.door(DoorType.FRONT_LEFT).windowState() } returns WindowState.DOWN

        // Mock encadeado para a Porta e aqui o WindowsState é DOWN
        // Realizando o encadeamento
        val door = car.door(DoorType.FRONT_LEFT)
        // O Estado padrão é UP
        car.door(DoorType.FRONT_LEFT).windowState()

        // Ambos passam, pois o door está encadeado com o: car.door(DoorType.FRONT_LEFT)
        assertEquals(WindowState.DOWN, door.windowState())
        assertEquals(WindowState.DOWN, car.door(DoorType.FRONT_LEFT).windowState())

        verify(exactly = 3) {
            car.door(DoorType.FRONT_LEFT).windowState()
        }
    }

    @Test
    fun `should not validate door on chained call`() {
        val car = mockk<Car>()

        // Lembre-se que aqui o Verify não passa, pois aqui se seta o comportamento.
        every { car.door(DoorType.FRONT_LEFT).windowState() } returns WindowState.DOWN

        // Mock encadeado para a Porta e aqui o WindowsState é DOWN
        // Realizando o encadeamento
        val door = car.door(DoorType.FRONT_LEFT)
        // O Estado padrão é UP
        car.door(DoorType.FRONT_LEFT).windowState()

        // Isso aqui fecha!
        assertEquals(WindowState.DOWN, door.type)

        verify {
            car.door(DoorType.FRONT_LEFT).windowState()
        }
    }

}
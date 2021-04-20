package com.example.testapplication.practice

import com.example.testapplication.Car
import com.example.testapplication.Direction
import com.example.testapplication.Outcome
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class SetupTest {

    @MockK
    private lateinit var car: Car

    /*
    Crio um Mock apartir de carro. Ele é o dublê da minha classe Car!
    Ele irá se comportar da forma que eu desejar, mas é preciso definir esse comportamento
    com o Every
    */
    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when i drive a car should return OK`() {
        /*
        Não importa o que eu for fazer, ele irá retornar OK!
        Pois eu estou dirigindo para uma direção

        every{} -> É a definição dos COMPORTAMENTOS para o mock
        */
        every { car.drive(Direction.NORTH) } returns Outcome.OK
        every { car.drive(Direction.SOUTH) } returns Outcome.OK

        car.drive(Direction.NORTH)
        car.drive(Direction.SOUTH)

        // Verifico se a direção do Carro é igual a NORTH
        verifySequence {
            car.drive(Direction.NORTH)
            car.drive(Direction.SOUTH)
        }

        // Confirma se a verificação ocorreu
        confirmVerified(car)
    }
}
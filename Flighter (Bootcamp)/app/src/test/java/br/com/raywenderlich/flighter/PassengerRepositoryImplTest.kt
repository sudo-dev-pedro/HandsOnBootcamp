package br.com.raywenderlich.flighter

import br.com.raywenderlich.flighter.dao.PassengerDAO
import br.com.raywenderlich.flighter.database.entity.Passenger
import br.com.raywenderlich.flighter.repository.PassengerRepositoryImpl
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test

class PassengerRepositoryImplTest {

    @MockK
    lateinit var passengerDAO: PassengerDAO

    @MockK
    lateinit var passenger: Passenger

    //Injeto pois meu passengerDAO será mockkado dentro
    @InjectMockKs(overrideValues = true)
    var passengerRepositoryImpl: PassengerRepositoryImpl = PassengerRepositoryImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when passed an passenger should insert`() {

        // Behavior (given)
        every {
            passengerDAO.insert(
                passenger = Passenger(
                    234,
                    "222",
                    "ph@email.com",
                    "12345",
                    "P",
                    "H",
                    "12/01/1990",
                    null
                )
            )
        } just Runs

        // When
        passenger = Passenger(
            234,
            "222",
            "ph@email.com",
            "12345",
            "P",
            "H",
            "12/01/1990",
            null
        )

        // Then
        passengerRepositoryImpl.insertPassenger(passenger)

        verify {
            passengerRepositoryImpl.insertPassenger(passenger)
        }
    }

    @Test
    fun `should get the passenger id`() {
        // Behavior (given)
        val id = 123L
        val email = "ph@mail.com"
        val password = "12345"

        every {
            passengerDAO.getUserId(
                email = email,
                password = password
            )
        } returns id

        val returnedId = passengerRepositoryImpl.getUserId(email = email, password = password)

        // Then
        assertEquals(123L, returnedId)
    }

    @Test
    fun `should not get the passenger id`() {
        // Behavior (given)
        val email = "ph@mail.com"
        val password = "12345"

        every {
            passengerDAO.getUserId(
                email = email,
                password = password
            )
        } returns null

        // When
        val returnedId = passengerRepositoryImpl.getUserId(email = email, password = password)

        // Then
        assertNull(returnedId)
    }

}
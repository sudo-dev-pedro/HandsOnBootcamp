package br.com.raywenderlich.flighter

import br.com.raywenderlich.flighter.dao.FlightDAO
import br.com.raywenderlich.flighter.database.entity.Flight
import br.com.raywenderlich.flighter.repository.FlightRepositoryImpl
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import java.util.*

class FlightRepositoryImplTest {

    @MockK
    lateinit var flightDAO: FlightDAO

    var flight = Flight(
        234,
        "Brasilia",
        "Curitiba",
        "BSB",
        "CWB",
        Date(),
        Date(),
        'B',
        'C',
        "C4",
        "M5",
        "2 Hours",
        "Good Flight",
        275.0,
        240,
        35,
        4590
    )

    //Injeto pois meu flightDAO será mockkado dentro
    @InjectMockKs(overrideValues = true)
    var flightRepositoryImpl: FlightRepositoryImpl = FlightRepositoryImpl()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when passed an passenger should insert`() {

        // Behavior (given)
        every {
            flightDAO.insert(flight)
        } just Runs

        // Then
        flightRepositoryImpl.insertFlight(flight)

        verify {
            flightRepositoryImpl.insertFlight(flight)
        }
    }

    @Test
    fun `when pass departure and arrival city should return flights list`() {
        val flights = listOf(
            flight,
            flight,
            flight
        )

        val departureCity = "Brasilia"
        val arrivalCity = "Curitiba"

        every {
            flightDAO.getFlightResults(departureCity = departureCity, arrivalCity = arrivalCity)
        } returns flights

        flightRepositoryImpl.getFlightResults(departureCity, arrivalCity)

        verify {
            flightRepositoryImpl.getFlightResults(departureCity, arrivalCity)
        }
    }

    @Test
    fun `when pass the flight id should return flight details`() {
        // Given
        every { flightDAO.getFlightDetails(flight.id) } returns flight

        // When
        flightRepositoryImpl.getFlightDetails(flight.id)

        // Then
        verify {
            flightRepositoryImpl.getFlightDetails(flight.id)
        }
    }

    @Test
    fun `when check flight availability should return true when is empty`() {
        val flight = flight.copy(passengersLimit = 240, passengersBooked = 0)

        // Given
        every { flightDAO.getFlightDetails(flight.id) } returns flight

        // When
        val response = flightRepositoryImpl.checkAvailability(40, flight.id)

        // Then
        assertTrue(response)
    }

    @Test
    fun `when check flight availability should return false when full`() {
        val flight = flight.copy(passengersLimit = 240, passengersBooked = 230)

        // Posso passar isso para o @Before, mas não seria para todos
        every { flightDAO.getFlightDetails(flight.id) } returns flight

        val response = flightRepositoryImpl.checkAvailability(40, flight.id)

        // Then
        assertFalse(response)
    }

    @Test
    fun `when check flight availability should return true with possible amount of passengers`() {
        every { flightDAO.getFlightDetails(flight.id) } returns flight

        val response = flightRepositoryImpl.checkAvailability(40, flight.id)

        // Then
        assertTrue(response)
    }

    @Test
    fun `when check flight availability should return false with passenger amount over limit`() {
        // Given
        every { flightDAO.getFlightDetails(flight.id) } returns flight

        // When
        val response = flightRepositoryImpl.checkAvailability(400, flight.id)

        // Then
        assertFalse(response)
    }


    @Test
    fun `should return false when flight doesnt exists`() {
        // Given
        every { flightDAO.getFlight(any()) } returns null

        // When
        val response = flightRepositoryImpl.checkAvailability(40, flight.id)

        // Then
        assertFalse(response)
    }
}
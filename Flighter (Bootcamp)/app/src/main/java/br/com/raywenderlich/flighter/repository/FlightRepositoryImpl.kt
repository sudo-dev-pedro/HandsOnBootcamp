package br.com.raywenderlich.flighter.repository

import androidx.lifecycle.LiveData
import br.com.raywenderlich.flighter.app.FlighterApplication
import br.com.raywenderlich.flighter.dao.FlightDAO
import br.com.raywenderlich.flighter.database.entity.Flight
import kotlinx.coroutines.awaitAll

class FlightRepositoryImpl : FlightRepository {

    private lateinit var flightDao: FlightDAO

    override fun insertFlight(flight: Flight) = flightDao.insert(flight)

    override fun getFlightResults(
        departureCity: String,
        arrivalCity: String
    ): List<Flight> = flightDao.getFlightResults(departureCity, arrivalCity)

    override fun getFlightDetails(id: Long): Flight = flightDao.getFlightDetails(id)

    override fun checkAvailability(passengers: Int, id: Long): Boolean {
        val flight: Flight? = getFlight(id)

        flight?.let {
            /* A quantidade de passageiros que desejam bookar
            precisar ser menor ou igual ao limit do voo */
            return flight.passengersBooked + passengers <= flight.passengersLimit
        }

        return false
    }

    private fun getFlight(id: Long): Flight? {
        return flightDao.getFlightDetails(id)
    }

    override fun deleteFlight(flight: Flight) {
        TODO("Not yet implemented")
    }
}
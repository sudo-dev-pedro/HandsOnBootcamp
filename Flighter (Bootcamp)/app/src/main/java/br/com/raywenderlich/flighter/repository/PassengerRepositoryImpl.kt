package br.com.raywenderlich.flighter.repository

import br.com.raywenderlich.flighter.app.FlighterApplication
import br.com.raywenderlich.flighter.dao.PassengerDAO
import br.com.raywenderlich.flighter.database.entity.Passenger

class PassengerRepositoryImpl : PassengerRepository {

    private lateinit var passengerDAO: PassengerDAO

    override fun insertPassenger(passenger: Passenger) =
        passengerDAO.insert(passenger)

    override fun getUserId(email: String, password: String): Long? =
        passengerDAO.getUserId(email, password)

    override fun deletePassengerData(passenger: Passenger) {
        TODO("Not yet implemented")
    }
}
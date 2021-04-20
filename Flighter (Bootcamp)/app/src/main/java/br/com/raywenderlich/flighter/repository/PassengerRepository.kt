package br.com.raywenderlich.flighter.repository

import br.com.raywenderlich.flighter.database.entity.Passenger

interface PassengerRepository {
    fun insertPassenger(passenger: Passenger)

    fun getUserId(email: String, password: String): Long?

    fun deletePassengerData(passenger: Passenger)
}
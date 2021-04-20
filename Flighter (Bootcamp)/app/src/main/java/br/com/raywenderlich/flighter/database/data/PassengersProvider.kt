package br.com.raywenderlich.flighter.database.data

import br.com.raywenderlich.flighter.database.entity.Passenger

// Singleton
object PassengersProvider {

    var passengersList = initPassengersList()

    private fun initPassengersList(): MutableList<Passenger> {
        val passengers = mutableListOf<Passenger>()
        passengers.add(
            Passenger(
                1,
                "11122233344",
                "passenger1@email.com",
                "abcdexyz",
                "Peter",
                "Oliveira",
                "10/09/1996"
            )
        )
        passengers.add(
            Passenger(
                367,
                "22211133344",
                "passenger367@email.com",
                "xyzdeabc",
                "Robin",
                "Hood",
                "01/06/1976"
            )
        )

        return passengers
    }
}
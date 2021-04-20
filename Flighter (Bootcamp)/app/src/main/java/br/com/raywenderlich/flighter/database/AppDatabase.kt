package br.com.raywenderlich.flighter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.raywenderlich.flighter.converters.DateConverter
import br.com.raywenderlich.flighter.dao.FlightDAO
import br.com.raywenderlich.flighter.dao.PassengerDAO
import br.com.raywenderlich.flighter.database.entity.Airplane
import br.com.raywenderlich.flighter.database.entity.Book
import br.com.raywenderlich.flighter.database.entity.Flight
import br.com.raywenderlich.flighter.database.entity.Passenger

@Database(
    entities = [
        (Airplane::class),
        (Book::class),
        (Flight::class),
        (Passenger::class)
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun passengerDAO(): PassengerDAO
    abstract fun flightDAO(): FlightDAO
}
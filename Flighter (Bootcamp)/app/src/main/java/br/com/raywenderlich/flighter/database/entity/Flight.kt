package br.com.raywenderlich.flighter.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.raywenderlich.flighter.database.DatabaseConstants.FLIGHT_TABLE_NAME
import java.util.Date

@Entity(
    tableName = FLIGHT_TABLE_NAME,
    foreignKeys = [
        (ForeignKey(
            entity = Airplane::class,
            parentColumns = ["id"],
            childColumns = ["airplane_id"],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.CASCADE
        ))
    ]
)

data class Flight(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Long,

    @ColumnInfo(name = "departure_city")
    val departureCity: String,

    @ColumnInfo(name = "arrival_city")
    val arrivalCity: String,

    @ColumnInfo(name = "departure_city_initials")
    val departureCityInitials: String,

    @ColumnInfo(name = "arrival_city_initials")
    val arrivalCityInitials: String,

    @ColumnInfo(name = "departure_date")
    val departureDate: Date,

    @ColumnInfo(name = "arrival_date")
    val arrivalDate: Date,

    @ColumnInfo(name = "departure_terminal")
    val departureTerminal: Char,

    @ColumnInfo(name = "arrival_terminal")
    val arrivalTerminal: Char,

    @ColumnInfo(name = "departure_gate")
    val departureGate: String,

    @ColumnInfo(name = "arrival_gate")
    val arrivalGate: String,

    @ColumnInfo(name = "estimated_flight_time")
    val estimatedFlightTime: String,

    @ColumnInfo(name = "airline_name")
    val airlineName: String,

    @ColumnInfo(name = "total_price")
    val totalPrice: Double,

    @ColumnInfo(name = "passengers_limit")
    val passengersLimit: Int,

    @ColumnInfo(name = "passengers_booked")
    val passengersBooked: Int,

    @ColumnInfo(name = "airplane_id")
    val airplaneID: Long
)

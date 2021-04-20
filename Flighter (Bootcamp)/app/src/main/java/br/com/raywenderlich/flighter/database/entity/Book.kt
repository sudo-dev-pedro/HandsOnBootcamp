package br.com.raywenderlich.flighter.database.entity

import androidx.room.*
import br.com.raywenderlich.flighter.database.DatabaseConstants.BOOK_TABLE_NAME

@Entity(
    tableName = BOOK_TABLE_NAME,
    foreignKeys = [
        (ForeignKey(
            entity = Passenger::class,
            parentColumns = ["id"],
            childColumns = ["passenger_id"])
        ),
        (ForeignKey(
            entity = Flight::class,
            parentColumns = ["id"],
            childColumns = ["flight_id"])
        )
    ],
    indices = [Index("passenger_id")]
)
data class Book(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Long,

    @ColumnInfo(name = "passenger_id")
    val passengerID: Long,

    @ColumnInfo(name = "flight_id")
    val flightID: Long
)
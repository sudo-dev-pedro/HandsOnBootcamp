package br.com.raywenderlich.flighter.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.raywenderlich.flighter.database.DatabaseConstants.PASSENGER_TABLE_NAME

@Entity(tableName = PASSENGER_TABLE_NAME)
data class Passenger(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Long,

    @ColumnInfo(name = "cpf")
    val cpf: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "birth_date")
    val birth_date: String,

    @ColumnInfo(name = "passport_code")
    val passportCode: String? = null
)
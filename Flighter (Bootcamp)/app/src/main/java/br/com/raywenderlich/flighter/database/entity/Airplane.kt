package br.com.raywenderlich.flighter.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.raywenderlich.flighter.database.DatabaseConstants.AIRPLANE_TABLE_NAME

@Entity(tableName = AIRPLANE_TABLE_NAME)
data class Airplane(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Long,

    @ColumnInfo(name = "model")
    val model: String,

    @ColumnInfo(name = "capacity")
    val capacity: Int
)
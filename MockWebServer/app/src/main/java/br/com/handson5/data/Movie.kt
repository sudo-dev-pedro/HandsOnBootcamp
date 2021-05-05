package br.com.handson5.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.handson5.database.DatabaseConstants.MOVIE_TABLE_NAME
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Entity(tableName = MOVIE_TABLE_NAME)
@JsonClass(generateAdapter = true)
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @Json(name = "description")
    @ColumnInfo(name = "description")
    val description: String = "",

    @Json(name = "image")
    @ColumnInfo(name = "image")
    val image: String = "",

    @Json(name = "title")
    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "favorite")
    val favorite: Int = 0
) : Serializable
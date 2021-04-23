package br.com.handson5.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.handson5.database.DatabaseConstants.USER_TABLE_NAME
import br.com.handson5.database.entity.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM $USER_TABLE_NAME WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): User?

    @Query("DELETE FROM $USER_TABLE_NAME WHERE username = :name")
    suspend fun deleteUserByName(name: String)
}
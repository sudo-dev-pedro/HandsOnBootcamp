package br.com.phro.room.database.dao

import androidx.room.*
import br.com.phro.room.database.entity.User
import br.com.phro.sqlite.database.DatabaseConstants.USER_TABLE_NAME

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM $USER_TABLE_NAME")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM $USER_TABLE_NAME WHERE id = :id")
    suspend fun getUser(id: Int?): User?

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM $USER_TABLE_NAME")
    suspend fun deleteUsers()

    @Query("DELETE FROM $USER_TABLE_NAME WHERE username = :name")
    suspend fun deleteUserByName(name: String)

}
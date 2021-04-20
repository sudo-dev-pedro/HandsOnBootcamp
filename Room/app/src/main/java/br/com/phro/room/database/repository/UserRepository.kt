package br.com.phro.room.database.repository

import br.com.phro.room.database.dao.UserDAO
import br.com.phro.room.database.entity.User

class UserRepository(private val userDAO: UserDAO) {

    suspend fun insert(user: User) {
        userDAO.insertUser(user)
    }

    suspend fun getUsers(): List<User> {
        return userDAO.getUsers()
    }

    suspend fun deleteUsers() {
        userDAO.deleteUsers()
    }

    suspend fun getUser(id: Int?): User? {
        return userDAO.getUser(id)
    }

    suspend fun updateUser(user: User) {
        userDAO.updateUser(user)
    }

    suspend fun deleteUserByName(name: String) {
        userDAO.deleteUserByName(name)
    }
}
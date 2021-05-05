package br.com.handson5.database.repository

import br.com.handson5.database.dao.UserDao
import br.com.handson5.database.entity.User

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUser(email: String, password: String): User? {
        return userDao.getUser(email, password)
    }
}
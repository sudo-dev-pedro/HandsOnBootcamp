package br.com.phro.sqlite.database.repository

import android.content.Context
import br.com.phro.sqlite.database.dao.UserDAO
import br.com.phro.sqlite.database.entity.User

class UserRepository(private val context: Context) {

    private val userDao = UserDAO(context)

    fun insert(user: User) {
        userDao.insertUser(user)
    }

    fun getUsers(): MutableList<User> {
        return userDao.getUsers()
    }

    fun deleteUsers() {
        userDao.deleteUsers()
    }

    fun getUser(id: Int?): User? {
        return userDao.getUser(id)
    }

    fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    fun deleteUserByName(name: String) {
        userDao.deleteUserByName(name)
    }
}
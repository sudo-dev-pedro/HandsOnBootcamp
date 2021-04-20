package br.com.phro.sqlite.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.phro.sqlite.database.entity.User
import br.com.phro.sqlite.database.repository.UserRepository

class MainViewModel : ViewModel() {
    private lateinit var userRepository: UserRepository

    private val _usersList = MutableLiveData<MutableList<User>>()
    val usersList: LiveData<MutableList<User>>
        get() = _usersList

    fun createRepository(context: Context) {
        userRepository = UserRepository(context)
    }

    fun addUser(user: User) {
        userRepository.insert(user)
        getUsers()
    }

    fun deleteAllUsers() {
        userRepository.deleteUsers()
    }

    fun deleteUserByName(name: String) {
        userRepository.deleteUserByName(name)
        getUsers()
    }

    private fun getUsers() {
        _usersList.postValue(userRepository.getUsers())
//        userRepository.getUsers()
    }
}
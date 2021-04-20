package br.com.phro.room.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.phro.room.database.dao.UserDAO
import br.com.phro.room.database.entity.User
import br.com.phro.room.database.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private lateinit var userRepository: UserRepository

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>>
        get() = _usersList

    fun createRepository(userDAO: UserDAO) {
        userRepository = UserRepository(userDAO)
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.insert(user)
            getUsers()
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch {
            userRepository.deleteUsers()
        }
    }

    fun deleteUserByName(name: String) {
        viewModelScope.launch {
            userRepository.deleteUserByName(name)
            getUsers()
        }
    }

    private suspend fun getUsers() {
        _usersList.postValue(userRepository.getUsers())
//        userRepository.getUsers()
    }
}
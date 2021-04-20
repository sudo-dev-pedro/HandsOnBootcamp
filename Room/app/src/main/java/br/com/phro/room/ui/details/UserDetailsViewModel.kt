package br.com.phro.room.ui.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.phro.room.database.dao.UserDAO
import br.com.phro.room.database.entity.User
import br.com.phro.room.database.repository.UserRepository
import kotlinx.coroutines.launch

class UserDetailsViewModel : ViewModel() {
    private lateinit var userRepository: UserRepository

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun createRepository(userDAO: UserDAO) {
        userRepository = UserRepository(userDAO)
    }

    fun getUser(id: Int?) {
        viewModelScope.launch {
            _user.postValue(userRepository.getUser(id))
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }
}
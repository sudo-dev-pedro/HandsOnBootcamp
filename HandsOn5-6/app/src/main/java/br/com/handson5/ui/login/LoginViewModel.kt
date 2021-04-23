package br.com.handson5.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.handson5.database.entity.User
import br.com.handson5.database.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    private val _userId = MutableLiveData<User>()
    val userId: LiveData<User>
        get() = _userId

    fun getUserId(username: String, password: String) {
        viewModelScope.launch {
            _userId.value = userRepository.getUser(username, password)
        }
    }
}
package br.com.handson5.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.handson5.database.entity.User
import br.com.handson5.database.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }
}
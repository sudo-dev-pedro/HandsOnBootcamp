package br.com.phro.sqlite.ui.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.phro.sqlite.database.entity.User
import br.com.phro.sqlite.database.repository.UserRepository

class UserDetailsViewModel : ViewModel() {
    private lateinit var userRepository: UserRepository

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun createRepository(context: Context) {
        userRepository = UserRepository(context)
    }

    fun getUser(id: Int?) {
        _user.postValue(userRepository.getUser(id))
    }

    fun updateUser(user: User) {
        userRepository.updateUser(user)
    }
}
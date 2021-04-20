package br.com.raywenderlich.flighter.ui.login

import androidx.lifecycle.ViewModel
import br.com.raywenderlich.flighter.repository.PassengerRepositoryImpl

// Essa ainda não é a melhor maneira de fazer isso! Factory é o que há!
class LoginViewModel(private val passengerRepository: PassengerRepositoryImpl): ViewModel() {

}
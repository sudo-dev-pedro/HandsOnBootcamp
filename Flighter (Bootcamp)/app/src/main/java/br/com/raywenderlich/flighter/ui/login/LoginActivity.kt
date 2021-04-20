package br.com.raywenderlich.flighter.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.raywenderlich.flighter.MainActivity
import br.com.raywenderlich.flighter.databinding.ActivityLoginBinding
import br.com.raywenderlich.flighter.repository.PassengerRepositoryImpl
import br.com.raywenderlich.flighter.ui.register.RegisterActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var view: ConstraintLayout
    private lateinit var passengerRepository: PassengerRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        view = loginBinding.root
        setContentView(view)

        passengerRepository = PassengerRepositoryImpl()

        grabPassengerCredentials()
    }

    private fun redirectPassenger() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
    }

    private fun redirectPassengerWithoutAccount() {
        startActivity(
            Intent(
                this,
                RegisterActivity::class.java
            )
        )
    }

    private fun logInPassenger(email: String, password: String) {
        lifecycleScope.launch {
            val userID = passengerRepository.getUserId(email, password)

            withContext(Main) {
                if (userID != null) {
                    redirectPassenger()
                } else {
                    redirectPassengerWithoutAccount()
                }
            }

        }
    }

    private fun grabPassengerCredentials() {
        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.emailInput.text.toString()
            val password = loginBinding.passwordInput.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                logInPassenger(email, password)
            }
        }
        createNewAccount()
    }

    private fun createNewAccount() {
        loginBinding.txtRegister.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )
        }
    }
}
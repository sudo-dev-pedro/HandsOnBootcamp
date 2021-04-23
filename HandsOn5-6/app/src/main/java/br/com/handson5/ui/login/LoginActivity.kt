package br.com.handson5.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.handson5.App.Companion.USER_HASH_CODE
import br.com.handson5.databinding.ActivityLoginBinding
import br.com.handson5.ui.main.MainActivity
import br.com.handson5.ui.register.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        if (sharedPreferences.contains(USER_HASH_CODE)) {
            redirectPassenger()
        }

        grabPassengerCredentials()
    }

    private fun redirectPassenger() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            ).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
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

    private fun grabPassengerCredentials() {
        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.usernameRegisterInput.text.toString()
            val password = loginBinding.passwordRegisterInput.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                logInPassenger(email, password)
            }
        }
        createNewAccount()
    }

    private fun logInPassenger(email: String, password: String) {
        loginViewModel.getUserId(email, password)

        loginViewModel.userId.observe(this) { userId ->
            sharedPreferences.edit()?.putInt(USER_HASH_CODE, email.hashCode())?.apply()

            if (userId != null) {
                redirectPassenger()
            } else {
                redirectPassengerWithoutAccount()
            }
        }
    }

    private fun createNewAccount() {
        loginBinding.txtRegister.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                ).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
            )
        }
    }
}
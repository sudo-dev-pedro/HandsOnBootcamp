package br.com.handson5.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.handson5.database.entity.User
import br.com.handson5.databinding.ActivityRegisterBinding
import br.com.handson5.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        grabUserData()
    }

    private fun grabUserData() {
        registerBinding.btnRegister.setOnClickListener {
            val username = registerBinding.emailRegisterInput.text.toString()
            val password = registerBinding.passwordRegisterInput.text.toString()

            val user = User(username = username, password = password)

            if (username.isNotBlank() && password.isNotBlank()) {
                registerUser(user)
            }
        }
    }

    private fun registerUser(user: User) {
        registerViewModel.insertUser(user)

        startActivity(
            Intent(
                this@RegisterActivity,
                MainActivity::class.java
            )
        )

    }
}
package br.com.raywenderlich.flighter.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import br.com.raywenderlich.flighter.MainActivity
import br.com.raywenderlich.flighter.R
import br.com.raywenderlich.flighter.database.entity.Passenger
import br.com.raywenderlich.flighter.databinding.ActivityLoginBinding
import br.com.raywenderlich.flighter.databinding.ActivityRegisterBinding
import br.com.raywenderlich.flighter.repository.PassengerRepositoryImpl
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var view: ConstraintLayout
    private lateinit var passengerRepository: PassengerRepositoryImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        view = registerBinding.root
        setContentView(view)

        passengerRepository = PassengerRepositoryImpl()

        grabPassengerData()
    }

    private fun registerPassenger(passenger: Passenger) {
        lifecycleScope.launch {
            passengerRepository.insertPassenger(passenger)

            withContext(Main) {
                startActivity(
                    Intent(
                        this@RegisterActivity,
                        MainActivity::class.java
                    )
                )
            }
        }
    }

    private fun grabPassengerData() {
        registerBinding.btnRegister.setOnClickListener {
            val cpf = registerBinding.cpfInput.text.toString()
            val firstName = registerBinding.firstNameInput.text.toString()
            val lastName = registerBinding.lastNameInput.text.toString()
            val birthDate = registerBinding.birthDateInput.text.toString()
            val email = registerBinding.emailInput.text.toString()
            val password = registerBinding.passwordInput.text.toString()
            val passport = registerBinding.passportInput.text?.toString()

            val id = cpf.hashCode().toLong()
            val passenger = Passenger(
                id, cpf, email, password, firstName, lastName, birthDate, passport
            )

            if (email.isNotBlank() && password.isNotBlank()) {
                registerPassenger(passenger)
            }
        }
    }
}
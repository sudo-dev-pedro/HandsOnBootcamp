package br.com.phro.biometric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.ERROR_NEGATIVE_BUTTON
import androidx.core.content.ContextCompat
import br.com.phro.biometric.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    //BiometricPrompt é a classe responsável por fazer a autenticação usando biometria
    private var biometricPrompt: BiometricPrompt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        // Em qual Thread irá ocorrer
        val executor = ContextCompat.getMainExecutor(this)

        if (biometricPrompt == null) {
            // O callback será aquilo que será executado após a autenticação.
            biometricPrompt = BiometricPrompt(this, executor, callback)
        }

        mainBinding.btnBiometric.setOnClickListener {
            checkAuthentication()
        }
    }

    // Podemos fazer uso de três funs
    private val callback: BiometricPrompt.AuthenticationCallback =
        object : BiometricPrompt.AuthenticationCallback() {

            // Em caso de erro: Cancela a autenticação e apresenta a mensagem
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                if (errorCode == ERROR_NEGATIVE_BUTTON && biometricPrompt != null) {
                    biometricPrompt?.cancelAuthentication()
                }
                showSnackbar(errString as String)
            }

            // Caso tudo dê certo.
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                showSnackbar("Deu bom! Pode prosseguir")
                val intent = Intent(
                    this@MainActivity,
                    PostAuthenticationActivity::class.java
                )

                startActivity(intent)
            }

            // Caso o Android não reconheça a digital, sendo ela diferente de alguma já cadastrada.
            override fun onAuthenticationFailed() {
                showSnackbar("Deu ruim!")
            }
        }

    // Informação a ser apresentada no Dialog de autenticação
    private fun buildBiometricPrompt(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Entrar")
            .setSubtitle("Autenticação Biométrica")
            .setDescription("Coloque o seu dedo sobre o sensor para desbloquear")
            .setNegativeButtonText("Cancelar")
            .build()
    }

    // Verificar se a autenticação é possível ou não por meio da impressão digital
    private fun checkAuthentication() {
        // Essa classe fornece informações relacionadas a Biometria (digital, facial e etc)
        val biometricManager = BiometricManager.from(this)

        // Se ele puder autenticar e se o Manager for igual a SUCCESS
        // Então irei buildar meu promptInfo e realizar a autenticação.
        if (biometricManager.canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_WEAK
            ) == BiometricManager.BIOMETRIC_SUCCESS
        ) {
            val promptInfo = buildBiometricPrompt()
            biometricPrompt?.authenticate(promptInfo)
        } else {
            showSnackbar("Não é possível realizar a autenticação biométrica!")
        }
    }

    private fun showSnackbar(text: String) {
        val view: View = mainBinding.mainView
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
    }
}
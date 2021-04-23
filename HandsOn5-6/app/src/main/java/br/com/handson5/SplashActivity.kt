package br.com.handson5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.handson5.databinding.ActivitySplashBinding
import br.com.handson5.ui.login.LoginActivity
import br.com.handson5.ui.main.MainActivity
import br.com.handson5.ui.main.MainViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding
    private lateinit var intentLogin: Intent
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        intentLogin = Intent(this, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        GlobalScope.launch {
            mainViewModel.searchMovies()
            delay(2000)
            withContext(Dispatchers.Main) {
                startActivity(intentLogin)
                finish()
            }
        }
    }
}
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
    private lateinit var job: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        job = CoroutineScope(Dispatchers.Main)

        intentLogin = Intent(this, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        job.launch {
            mainViewModel.searchMovies()
            delay(3000)
            withContext(Dispatchers.Main) {
                startActivity(intentLogin)
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
        finish()
    }
}
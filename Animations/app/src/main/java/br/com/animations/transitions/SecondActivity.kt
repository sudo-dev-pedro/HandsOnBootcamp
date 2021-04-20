package br.com.animations.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.animations.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = "Second Activity"
    }
}
package br.com.handson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.handson4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var view: View
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        view = mainBinding.root

        setContentView(view)

        mainBinding.btnCustom.setOnValueChangeListener { value ->
            mainBinding.counterNumber.text = value.toString()
        }
    }
}
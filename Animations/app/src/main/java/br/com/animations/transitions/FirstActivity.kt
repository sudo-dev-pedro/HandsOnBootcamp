package br.com.animations.transitions

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.animations.R
import br.com.animations.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var view: View
    private lateinit var activityBinding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = ActivityFirstBinding.inflate(layoutInflater)
        view = activityBinding.root
        setContentView(view)

        title = "First Activity"

        activityBinding.btnNext.setOnClickListener {
            val intent = Intent(
                this,
                SecondActivity::class.java
            )
            startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        }

    }
}
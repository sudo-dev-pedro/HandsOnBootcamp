package br.com.raywenderlich.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.raywenderlich.customviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var valueSelectionBinding: ActivityMainBinding
    private lateinit var view: View

    private var graphicPercentage = 0f
    private var angle = 0f

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        valueSelectionBinding = ActivityMainBinding.inflate(layoutInflater)
        view = valueSelectionBinding.root

        setContentView(view)

        // Pegar os valores toda vez que o usuário realizar o clique
        valueSelectionBinding.viewSelectionValue.setOnValueChangeListener { value, maxValue ->
            graphicPercentage = ((value * 100) / maxValue).toFloat()
            angle = (graphicPercentage / 100) * 360

            valueSelectionBinding.graphicChart.getAngle(angle)
        }

        /*
        btnReset.setOnClickListener {
            valueSelectionBinding.resetvalue()
        }
        */
    }
}
package br.com.raywenderlich.customviews.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.raywenderlich.customviews.R

// Pressione alt + insert para implementar os construtores.
class ValueSelectionView : ConstraintLayout {

    private lateinit var lessButton: ImageButton
    private lateinit var plusButton: ImageButton
    private lateinit var txtValue: TextView

    private var value = 0
    private var maxValue = 100

    // Listener para fazer com que eu utilize os seguintes valores em outra View.
    var valueChangeListener: (
        value: Int,
        maxValue: Int
    ) -> Unit = { _, _ -> }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        lessButton = findViewById(R.id.btnLess)
        plusButton = findViewById(R.id.btnPlus)
        txtValue = findViewById(R.id.txtNumber)

        lessButton.setOnClickListener {
            if (value > 0) {
                txtValue.text = (--value).toString()
                valueChangeListener(value, maxValue)
            }
        }

        plusButton.setOnClickListener {
            if (value < maxValue) {
                txtValue.text = (++value).toString()
                valueChangeListener(value, maxValue)
            }
        }
    }

    private fun init(attrs: AttributeSet?) {
        inflate(context, R.layout.view_value_selection, this)

        val viewAttributesArray = context.obtainStyledAttributes(
            attrs, R.styleable.ValueSelectionView
        )

        try {
            // Esse atributo pode agora ser utilizado na minha CustomView!!
            // Lá pode ser setado um valor ou então ele fará uso desse defValue que defini.
            maxValue = viewAttributesArray.getInt(R.styleable.ValueSelectionView_maxValue, 100)
        } finally {
            // Isso aqui pode lançar uma RuntimeException se for reciclada mais de uma vez
            // TypedArray ->
            viewAttributesArray.recycle()
        }

    }

    fun setOnValueChangeListener(listener: (Int, Int) -> Unit) {
        valueChangeListener = listener
    }

    fun resetValue() {
        value = 0
        txtValue.text = value.toString()
        valueChangeListener(value, maxValue)
    }
}
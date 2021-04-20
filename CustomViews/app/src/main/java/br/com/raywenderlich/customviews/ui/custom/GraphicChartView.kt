package br.com.raywenderlich.customviews.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import br.com.raywenderlich.customviews.R
import kotlin.math.min

class GraphicChartView : View {

    private var viewWidth = 0
    private var viewHeight = 0
    private var angle = 270f

    private var backgrounCircleColor = Color.LTGRAY
    private var foregroundCircleColor = Color.CYAN
    private var centerCircleColor = Color.WHITE

    private val paint = Paint()

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val viewAttributesArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.GraphicChartView
        )

        try {
            // Esse atributo pode agora ser utilizado na minha CustomView!!
            // Lá pode ser setado um valor ou então ele fará uso desse defValue que defini.
            angle = viewAttributesArray.getFloat(R.styleable.GraphicChartView_angle, 270f)

            backgrounCircleColor = viewAttributesArray.getColor(
                R.styleable.GraphicChartView_backgroundCircleColor,
                Color.LTGRAY
            )

            foregroundCircleColor = viewAttributesArray.getColor(
                R.styleable.GraphicChartView_foregroundCircleColor,
                Color.CYAN
            )

            centerCircleColor = viewAttributesArray.getColor(
                R.styleable.GraphicChartView_foregroundCircleColor,
                Color.WHITE
            )

        } finally {
            viewAttributesArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {

            paint.color = backgrounCircleColor
            it.drawCircle(
                viewWidth / 2f,
                viewHeight / 2f,
                min(viewWidth / 2, viewHeight / 2).toFloat(),
                paint
            )

            //Desenho do arco 0,0 consideram o canto superior esquerdo.
            paint.color = foregroundCircleColor
            it.drawArc(
                0f,
                0f,
                viewWidth.toFloat(),
                viewHeight.toFloat(),
                0f,
                angle,
                true,
                paint
            )

            paint.color = centerCircleColor
            it.drawCircle(
                viewWidth / 2f,
                viewHeight / 2f,
                min(viewWidth / 4, viewHeight / 4).toFloat(),
                paint
            )
        }
    }

    fun getAngle(angle: Float) {
        this.angle = angle
        invalidate()
    }
}
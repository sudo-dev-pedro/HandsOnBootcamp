package br.com.handson4.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import br.com.handson4.R
import kotlin.math.min
import kotlin.random.Random

class CustomButton : View {

    private var counter = 0

    private var currentRotation = 180f

    private var viewWidth = 0
    private var viewHeight = 0
    private var shadowRadius = 0f

    private var screenCenterX = 0f
    private var screenCenterY = 0f

    private var intervals = 10
    private var currentAngle = 0f // onSaveInstanceState
    private var rotationIntervals = 360f / intervals

    private var backgroundCircleColor = Color.LTGRAY
    private var backgroundIndicatorColor = Color.BLACK
    private val randomColor = Random.Default

    private val paint = Paint()

    var valueCounterListener: (value: Int) -> Unit = { _ -> }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val customButtonAttributesArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomButton
        )

        try {
            backgroundCircleColor = customButtonAttributesArray.getColor(
                R.styleable.CustomButton_backgroundCircleColor, Color.LTGRAY
            )

            backgroundIndicatorColor = customButtonAttributesArray.getColor(
                R.styleable.CustomButton_backgroundIndicatorColor, Color.BLACK
            )

            intervals = customButtonAttributesArray.getColor(
                R.styleable.CustomButton_interval, 10
            )

            rotationIntervals = 360f / intervals

        } finally {
            customButtonAttributesArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(widthMeasureSpec)

        screenCenterX = viewWidth / 2f
        screenCenterY = viewHeight / 2f

        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {

            it.rotate(currentRotation, screenCenterX, screenCenterY)

            shadowRadius = min(viewWidth / 3f, viewHeight / 3f)

            paint.setShadowLayer(
                shadowRadius,
                0f,
                0f,
                Color.LTGRAY
//                Color.argb(
//                    255,
//                    randomColor.nextInt(256),
//                    randomColor.nextInt(256),
//                    randomColor.nextInt(256)
//                )
            )

            setLayerType(LAYER_TYPE_SOFTWARE, paint)

            // Circulo principal
            paint.color = backgroundCircleColor
            it.drawCircle(
                screenCenterX,
                screenCenterY,
                min(viewWidth / 3f, viewHeight / 3f),
                paint
            )

            paint.clearShadowLayer()

            // Circulo indicador
            paint.color = backgroundIndicatorColor
            it.drawCircle(
                screenCenterX,
                screenCenterY - (min(viewWidth / 4f, viewHeight / 4f)),
                min(viewWidth / 32f, viewHeight / 32f),
                paint
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {

            if (event.x > screenCenterX) {
                if (counter < intervals) {
                    counter++
                    currentRotation += rotationIntervals
                }
            } else {
                if (counter > 0) {
                    counter--
                    currentRotation -= rotationIntervals
                }
            }

            valueCounterListener(counter)
            invalidate()
        }
        return true
    }

    fun setOnValueChangeListener(valueChangeListener: (Int) -> Unit) {
        valueCounterListener = valueChangeListener
    }

    companion object {
        private const val CURRENT_ANGLE_KEY = 0f
    }
}
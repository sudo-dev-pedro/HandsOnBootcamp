package br.com.animations

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import br.com.animations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        view = mainBinding.root

        setContentView(view)

        startAnimations()
    }

    private fun startAnimations() {
        mainBinding.btnStart.setOnClickListener {
            val animatorSet = AnimatorSet().apply {
                play(startAnimatorOne()).before(startAnimatorTwo())
            }
            animatorSet.start()
        }
    }

    // Animation 1
    private fun startAnimatorOne(): Animator {
        return ObjectAnimator
            .ofFloat(
                mainBinding.imageOne,
                "translationX",
                750f
            ).apply {
                duration = 500
                interpolator = AccelerateDecelerateInterpolator()
            }
    }

    // Animation 2
    private fun startAnimatorTwo(): Animator {
        return ObjectAnimator
            .ofFloat(
                mainBinding.imageTwo,
                "translationX",
                750f
            ).apply {
                duration = 1000
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        super.onAnimationEnd(animation, isReverse)
                        createAnimationWhichChangeColor()
                    }
                })
            }
    }

    // Animation 3
    private fun createAnimationWhichChangeColor() {
        val animatorWhichChangeColor = ObjectAnimator
            .ofFloat(
                mainBinding.imageThree,
                "translationX",
                750f
            ).apply {
                duration = 1000

                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        super.onAnimationStart(animation)
                        mainBinding.imageThree.background =
                            getDrawable(R.drawable.shape_white)
                    }

                    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        super.onAnimationEnd(animation, isReverse)
                        mainBinding.imageThree.background =
                            getDrawable(R.drawable.shape_teal)
                        startAnimatorReverse()
                    }
                })
            }
        animatorWhichChangeColor.start()
    }

    // Animation 4
    private fun startAnimatorReverse() {
        val animatorReverse = ObjectAnimator
            .ofFloat(
                mainBinding.imageFour,
                "translationX",
                750f
            ).apply {
                duration = 1000
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        super.onAnimationEnd(animation, isReverse)
                        startTextAnimator360()
                    }
                })
                repeatCount = 1
                repeatMode = ValueAnimator.REVERSE
            }
        animatorReverse.start()
    }

    // Animation 5
    private fun startTextAnimator360() {
        val textRotationAnimator360 = ObjectAnimator
            .ofFloat(
                mainBinding.txtRotate360,
                "rotation",
                360f
            ).apply {
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        super.onAnimationEnd(animation)
                        startTextViewRotateAnimation()
                    }
                })
            }
        textRotationAnimator360.start()
    }

    // Animation 6
    private fun startTextViewRotateAnimation() {
        val textRotationAnimator = ObjectAnimator
            .ofFloat(
                mainBinding.txtRotation,
                "rotation",
                360f
            ).apply {
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        super.onAnimationEnd(animation, isReverse)
                        startBallsBounceAnimator()
                    }
                })
            }
        textRotationAnimator.start()
    }

    // Animation 7
    private fun startBallsBounceAnimator() {
        val animator360 = ObjectAnimator
            .ofFloat(
                mainBinding.containerImages,
                "translationY",
                70f
            ).apply {
                duration = 500
                repeatMode = ValueAnimator.REVERSE
                repeatCount = 5
            }
        animator360.start()
    }
}
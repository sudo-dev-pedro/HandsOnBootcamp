package br.com.handson5.ui.moviedetails

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.lifecycle.ViewModelProvider
import br.com.handson5.databinding.ActivityMovieDetailBinding
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityMovieDetailBinding
    private val movieDetailsViewModel by lazy {
        ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        title = intent.getStringExtra(EXTRA_MOVIE_NAME)

        showMovieDetails()
        onClickDownloadButton()
        observeWorkResultInfo()
    }

    private fun observeWorkResultInfo() {
        movieDetailsViewModel.workResultInfo.observe(this) { resultInfo ->
            if (resultInfo) {
                dealWithResultSuccess()
            } else {
                dealWithResultFailed()
            }
        }
    }

    private fun addRotationInfinite(imageRotation: ImageView) {
        imageRotation.visibility = View.VISIBLE
        ObjectAnimator.ofFloat(
            imageRotation,
            ViewAnimator.ROTATION,
            -360f
        ).apply {
            duration = 1000
            interpolator = DecelerateInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }

    private fun addCompleteAnimation(imageDownloadComplete: ImageView) {
        imageDownloadComplete.visibility = View.VISIBLE

        ObjectAnimator.ofFloat(
            imageDownloadComplete,
            ViewAnimator.ROTATION,
            360f
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    detailBinding.imageRotation.visibility = View.GONE
                }
            })
            duration = 1000
            interpolator = BounceInterpolator()
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }

    private fun onClickDownloadButton() {
        detailBinding.btnDowload.setOnClickListener {
            addRotationInfinite(detailBinding.imageRotation)
            movieDetailsViewModel.startDownloadWorker(this, this)
        }
    }

    private fun dealWithResultSuccess() {
        detailBinding.imageRotation.visibility = View.GONE
        addCompleteAnimation(detailBinding.imageDownloadComplete)
        showToast("Download completo!")
        detailBinding.imageDownloadComplete.visibility = View.VISIBLE
    }

    private fun dealWithResultFailed() {
        detailBinding.imageDownloadComplete.visibility = View.GONE
        showToast("Download falhou!")
    }

    private fun showToast(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        )
            .show()
    }

    private fun showMovieDetails() {
        detailBinding.titleDescriptionMovie.text = intent.getStringExtra(EXTRA_MOVIE_NAME)
        detailBinding.descriptionMovie.text = intent.getStringExtra(EXTRA_MOVIE_DESCRIPTION)
        intent.getStringExtra(EXTRA_MOVIE_IMAGE)?.let {
            loadImage(it)
        }
    }

    private fun loadImage(uri: String) {
        Glide
            .with(this)
            .load(uri)
            .into(detailBinding.imageDescriptionMovie)
    }

    companion object {
        const val EXTRA_MOVIE_IMAGE = "EXTRA_MOVIE_IMAGE"
        const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        const val EXTRA_MOVIE_DESCRIPTION = "EXTRA_MOVIE_DESCRIPTION"
        const val DOWNLOAD_WORKKER = "WORKER"
    }
}
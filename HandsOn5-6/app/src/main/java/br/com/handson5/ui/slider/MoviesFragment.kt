package br.com.handson5.ui.slider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.handson5.R
import br.com.handson5.data.Movie
import com.bumptech.glide.Glide

class MoviesFragment : Fragment() {

    private lateinit var movieTitle: TextView
    private lateinit var movieDescription: TextView
    private lateinit var movieImage: ImageView

//    private lateinit var titleMovie: String
//    private lateinit var descriptionMovie: String
//    private lateinit var imageMOvie: String

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.get(EXTRA_MOVIE)?.let {
//            populateViews(it as Movie)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(
            R.layout.fragment_movies,
            container,
            false
        )

        arguments?.get(EXTRA_MOVIES_ARGUMENT)?.let {
            populateViews(view, it as Movie)
        }

        return view
    }

    private fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide
            .with(this)
            .load(imageUrl)
            .into(imageView)
    }

    private fun populateViews(view: View, movie: Movie) {

        movieTitle = view.findViewById(R.id.txtSliderTitleMovie)
        movieDescription = view.findViewById(R.id.txtSliderDescriptionMovie)
        movieImage = view.findViewById(R.id.imageSliderMovie)

        movieTitle.text = movie.title
        movieDescription.text = movie.description
        loadImage(movie.image, movieImage)
    }

    companion object {
//        private const val ARG_PARAM1 = "param1"
//        private const val ARG_PARAM2 = "param2"
//        private const val ARG_PARAM3 = "param3"

        const val EXTRA_MOVIES_ARGUMENT = "MOVIE"

//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            MoviesFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}
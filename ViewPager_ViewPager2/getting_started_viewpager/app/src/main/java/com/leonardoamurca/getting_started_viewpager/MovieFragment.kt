package com.leonardoamurca.getting_started_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class MovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val ratingTextView = view.findViewById<TextView>(R.id.ratingTextView)
        val posterImageView = view.findViewById<ImageView>(R.id.posterImageView)
        val overviewTextView = view.findViewById<TextView>(R.id.overviewTextView)

        arguments?.let { args ->
            titleTextView.text = args.getString(MovieHelper.KEY_TITLE)
            ratingTextView.text = String.format("%d/10", args.getInt(MovieHelper.KEY_RATING))
            overviewTextView.text = args.getString(MovieHelper.KEY_OVERVIEW)

            Picasso
                .with(activity)
                .load(
                    resources.getIdentifier(
                        args.getString(MovieHelper.KEY_POSTER_URI),
                        "drawable",
                        activity?.packageName
                    )
                )
                .into(posterImageView)
        }

        return view
    }

    companion object {
        fun newInstance(movie: Movie): MovieFragment {
            return MovieFragment().apply {
                arguments = bundleOf(
                    MovieHelper.KEY_TITLE to movie.title,
                    MovieHelper.KEY_RATING to movie.rating,
                    MovieHelper.KEY_POSTER_URI to movie.posterUri,
                    MovieHelper.KEY_OVERVIEW to movie.overview
                )
            }
        }
    }

}
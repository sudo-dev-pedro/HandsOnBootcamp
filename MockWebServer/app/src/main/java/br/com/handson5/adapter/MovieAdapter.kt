package br.com.handson5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.handson5.R
import br.com.handson5.data.Movie
import br.com.handson5.viewholder.MovieViewHolder
import com.bumptech.glide.Glide

class MovieAdapter(
    private var moviesList: List<Movie>,
    private val onItemClick: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_movie,
                parent,
                false
            )

        return MovieViewHolder(item)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemView.apply {
            Glide
                .with(holder.itemView.context)
                .load(moviesList[position].image)
                .into(holder.imageMovie)

            holder.titleMovie.text = moviesList[position].title
            holder.descriptionMovie.text = moviesList[position].description

            setOnClickListener {
                onItemClick(moviesList[position])
            }
        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun updateMovieList(newMoviesList: List<Movie>) {
        moviesList = newMoviesList
        notifyDataSetChanged()
    }

    fun getList(): List<Movie> = moviesList
}
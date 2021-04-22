package br.com.raywenderlich.jikan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.jikan.R
import br.com.raywenderlich.jikan.models.Anime
import br.com.raywenderlich.jikan.viewholder.AnimesViewHolder
import com.bumptech.glide.Glide

class AnimesAdapter : RecyclerView.Adapter<AnimesViewHolder>() {

    private var animesList: List<Anime> = emptyList()
    private var onClickAction: (anime: Anime) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimesViewHolder {
        val item = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_anime,
                parent,
                false
            )

        return AnimesViewHolder(item)
    }

    override fun onBindViewHolder(holder: AnimesViewHolder, position: Int) {
        holder.itemView.apply {
            Glide
                .with(holder.itemView.context)
                .load(animesList[position].imageUrl)
                .into(holder.imgAnime)

            holder.animeName.text = animesList[position].title
            holder.animeSynopsis.text = animesList[position].synopsis

            setOnClickListener {
                onClickAction(animesList[position])
            }
        }
    }

    fun updateAnimesList(newAnimesList: List<Anime>) {
        animesList = newAnimesList
    }

    fun onClickItem(anime: (Anime) -> Unit) {
        onClickAction = anime
    }

    override fun getItemCount(): Int = animesList.size

}
package br.com.raywenderlich.jikan.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.jikan.R

// Posso usar esse mesmo VH para os meu dois Adapters
class AnimesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgAnime: ImageView = itemView.findViewById(R.id.imgAnime)
    val animeName: TextView = itemView.findViewById(R.id.txtAnimeTitle)
    val animeSynopsis: TextView = itemView.findViewById(R.id.txtAnimeSynopsis)
}
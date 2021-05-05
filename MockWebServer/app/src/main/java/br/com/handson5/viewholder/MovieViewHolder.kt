package br.com.handson5.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.handson5.R

class MovieViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    val imageMovie: ImageView = itemView.findViewById(R.id.imageMovie)
    val titleMovie: TextView = itemView.findViewById(R.id.titleMovie)
    val descriptionMovie: TextView = itemView.findViewById(R.id.descriptionMovie)
}
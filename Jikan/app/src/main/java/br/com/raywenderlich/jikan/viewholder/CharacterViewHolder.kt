package br.com.raywenderlich.jikan.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.jikan.R

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val characterImage: ImageView = itemView.findViewById(R.id.imgCharacterOrStaff)
    val characterName: TextView = itemView.findViewById(R.id.txtCharacterOrStaffName)
    val roleName: TextView = itemView.findViewById(R.id.txtRole)
}
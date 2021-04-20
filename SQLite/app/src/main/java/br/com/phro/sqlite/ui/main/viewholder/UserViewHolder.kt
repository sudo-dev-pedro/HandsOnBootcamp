package br.com.phro.sqlite.ui.main.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.phro.sqlite.R

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val userName: TextView = itemView.findViewById(R.id.tvItemUserName)
    val userPassword: TextView = itemView.findViewById(R.id.tvItemPassword)
}
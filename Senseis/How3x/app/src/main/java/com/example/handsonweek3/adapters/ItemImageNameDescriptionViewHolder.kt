package com.example.handsonweek3.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.handsonweek3.R

// View holder used to render both recycler view lists because they have the same format.
class ItemImageNameDescriptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.image)
    val name: TextView = view.findViewById(R.id.name)
    val description: TextView = view.findViewById(R.id.description)
}
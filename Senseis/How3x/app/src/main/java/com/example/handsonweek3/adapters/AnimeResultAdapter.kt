package com.example.handsonweek3.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handsonweek3.R
import com.example.handsonweek3.services.SearchAnimeResult
import com.example.handsonweek3.tasks.doAsync
import java.io.InputStream
import java.net.URL


class AnimeResultAdapter(
    initialItems: List<SearchAnimeResult>,
    private val onItemClick: (value: SearchAnimeResult) -> Unit
) : RecyclerView.Adapter<ItemImageNameDescriptionViewHolder>() {

    private var items: List<SearchAnimeResult> = initialItems

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<SearchAnimeResult>) {
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageNameDescriptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ItemImageNameDescriptionViewHolder(inflater.inflate(R.layout.item_image_name_desc, parent, false))
    }

    override fun onBindViewHolder(holder: ItemImageNameDescriptionViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnClickListener { onItemClick.invoke(item) }

        // Initializing a task to download the image to put it on the ImageView.
        doAsync {
            // Downloading the bitmap inside the task.
            val bitmap = BitmapFactory.decodeStream(URL(item.imageUrl).content as InputStream)

            // Setting the downloaded image to the ImageView.
            holder.image.post { holder.image.setImageBitmap(bitmap) }
        }

        holder.name.text = item.title
        holder.description.text = item.synopsis
    }
}
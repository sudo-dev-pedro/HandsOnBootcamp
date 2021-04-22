package br.com.raywenderlich.jikan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.jikan.R
import br.com.raywenderlich.jikan.models.CharacterResult
import br.com.raywenderlich.jikan.viewholder.CharacterViewHolder
import com.bumptech.glide.Glide

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private var characterList: List<CharacterResult> = emptyList()

    fun updateList(newCharacterList: List<CharacterResult>) {
        characterList = newCharacterList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val item = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_character_staff,
                parent,
                false
            )

        return CharacterViewHolder(item)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.itemView.apply {
            Glide
                .with(holder.itemView.context)
                .load(characterList[position].imgUrl)
                .into(holder.characterImage)
            holder.characterName.text = characterList[position].name
            holder.roleName.text = characterList[position].role
        }
    }

    override fun getItemCount(): Int = characterList.size

}
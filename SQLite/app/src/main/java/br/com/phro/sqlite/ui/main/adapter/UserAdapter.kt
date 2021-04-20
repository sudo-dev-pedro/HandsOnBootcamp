package br.com.phro.sqlite.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.phro.sqlite.R
import br.com.phro.sqlite.database.entity.User
import br.com.phro.sqlite.ui.main.viewholder.UserViewHolder

class UserAdapter(
    private var userList: List<User>,
    private val onItemClick: (id: Int?) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val item = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_user,
                parent,
                false
            )

        return UserViewHolder(item)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.apply {
            holder.userName.text = userList[position].username
            holder.userPassword.text = userList[position].password
            setOnClickListener {
                onItemClick(userList[position].id)
            }
        }
    }

    fun updateUsersList(newMoviesList: List<User>) {
        userList = newMoviesList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = userList.size
}
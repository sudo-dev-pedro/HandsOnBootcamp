package com.example.designpatterns.patterns.structural.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*
Aplicação do Design Pattern Adapter onde um conjuntos de informações desconhecida pelo RecyclerView é adaptado atráves da classe para que
seja conhecido e usado.
*/
class ListAdapter(private val objects: List<Any>) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(0, parent)
        return MyViewHolder(view)
    }

    override fun getItemCount() = objects.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(objects[position])
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(obj: Any) {

        }
    }
}
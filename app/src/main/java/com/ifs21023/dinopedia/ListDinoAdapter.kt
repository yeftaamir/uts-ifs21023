package com.ifs21023.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ListDinoAdapter {private val listDinosaurus: ArrayList<Dinosaurus>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {
        private lateinit var onItemClickCallback: OnItemClickCallback
        fun setOnItemClickCallback(onItemClickCallback:
                                   OnItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback
        }
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
        Int): ListViewHolder {
            val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
            return ListViewHolder(binding)
        }
        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ListViewHolder, position:
        Int) {
            val dino = listDinosaurus[position]
            holder.binding.ivItemDino.setImageResource(dino.icon)
            holder.binding.tvItemDino.text = dino.name
            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listDino[holder.adapterPosition])
            }
        }
        override fun getItemCount(): Int = listDino.size
        class ListViewHolder(var binding: ItemRowDinoBinding) :
            RecyclerView.ViewHolder(binding.root)
        interface OnItemClickCallback {
            fun onItemClicked(data: Dino)
        }
    }
}
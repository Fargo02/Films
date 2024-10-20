package com.example.films.ui.films.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.films.databinding.GenreItemBinding

class GenresViewHolder(
    private val binding: GenreItemBinding,
    private val clickListener: GenresAdapter.GenreClickListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: String) {
        binding.name.text = model
        itemView.setOnClickListener { clickListener.onGenreClick(model, layoutPosition) }
    }
}
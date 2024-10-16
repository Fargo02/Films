package com.example.films.ui.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.films.databinding.FilmItemBinding
import com.example.films.domain.films.model.Film


class FilmsViewHolder(
    private val binding: FilmItemBinding,
    private val clickListener: FilmsAdapter.TrackClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Film) {

        itemView.setOnClickListener { clickListener.onFilmClick(model) }

    }
}
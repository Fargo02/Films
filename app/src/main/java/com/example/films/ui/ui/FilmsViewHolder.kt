package com.example.films.ui.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.films.R
import com.example.films.databinding.FilmItemBinding
import com.example.films.domain.films.model.Film

class FilmsViewHolder(
    private val binding: FilmItemBinding,
    private val clickListener: FilmsAdapter.FilmClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Film) {
        binding.name.text = model.localizedName
        Glide.with(itemView)
            .load(model.imageUrl)
            .transform(CenterCrop(), RoundedCorners(itemView.context.resources.getDimensionPixelSize(R.dimen.mark_4dp)))
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.imageCover)

        itemView.setOnClickListener { clickListener.onFilmClick(model) }

    }
}
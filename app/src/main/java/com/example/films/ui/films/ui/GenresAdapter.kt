package com.example.films.ui.films.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.films.databinding.GenreItemBinding

class GenresAdapter(
    private val clickListener: GenreClickListener
): RecyclerView.Adapter<GenresViewHolder>() {

    var genres = ArrayList<String>()
    var selectedPosition = -2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return GenresViewHolder(GenreItemBinding.inflate(layoutInspector, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(genres[position])
        holder.itemView.isSelected = position == selectedPosition

        holder.itemView.setOnClickListener {
            var currentGenre = ""
            if (position == selectedPosition) {
                selectedPosition = -1
            } else {
                selectedPosition = holder.adapterPosition
                currentGenre = genres[position]
            }
            notifyDataSetChanged()
            clickListener.onGenreClick(currentGenre, selectedPosition)
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    fun interface GenreClickListener {
        fun onGenreClick(name: String, position: Int)
    }
}
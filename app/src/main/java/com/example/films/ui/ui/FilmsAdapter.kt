package com.example.films.ui.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.films.databinding.FilmItemBinding
import com.example.films.domain.films.model.Film

class FilmsAdapter(private val clickListener: FilmClickListener) :
    RecyclerView.Adapter<FilmsViewHolder>() {

    var films = ArrayList<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return FilmsViewHolder(FilmItemBinding.inflate(layoutInspector, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int = films.size

    fun interface FilmClickListener {
        fun onFilmClick(film: Film)
    }
}
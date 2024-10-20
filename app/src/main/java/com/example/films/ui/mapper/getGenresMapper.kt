package com.example.films.ui.mapper

import com.example.films.domain.films.model.Film

fun getGenresMapper(foundFilms: List<Film>?): List<String> {
    val genres = foundFilms
        ?.flatMap { film -> film.genres }
        ?.toSet()
        ?.map { it.capitalize() }
        ?.sorted() ?: emptySet()
    return genres.toList()
}
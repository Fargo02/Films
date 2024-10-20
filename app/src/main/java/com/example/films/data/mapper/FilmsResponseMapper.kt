package com.example.films.data.mapper

import com.example.films.data.search.dto.FilmsSearchResponse
import com.example.films.domain.films.model.Film

class FilmsResponseMapper() {
    fun map(response: FilmsSearchResponse): List<Film> {
        return response.films.map { film ->
                Film(
                    id = film.id,
                    localizedName = film.localized_name ?: "",
                    name = film.name ?: "",
                    year = film.year ?: "",
                    rating = film.rating,
                    imageUrl = film.image_url ?: "" ,
                    description = film.description ?: "",
                    genres = film.genres
                )
        }.sortedBy { it.localizedName }
    }
}
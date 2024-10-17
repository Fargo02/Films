package com.example.films.data.mapper

import com.example.films.data.search.dto.FilmsSearchResponse
import com.example.films.domain.films.model.Film

class FilmsResponseMapper() {
    fun map(response: FilmsSearchResponse): List<Film> {
        return if (response.films == null) {
            emptyList<Film>()
        } else {
            response.films.map { film ->
                Film(
                    id = film.id,
                    localized_name = film.localized_name ?: "",
                    name = film.name ?: "",
                    year = film.year ?: "",
                    rating = film.rating,
                    image_url = film.image_url ?: "" ,
                    description = film.description ?: "",
                    genres = film.genres
                )
            }
        }
    }
}
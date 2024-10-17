package com.example.films.data.search.dto

data class FilmDto(
    val id: Long,
    val localized_name: String?,
    val name: String?,
    val year: String?,
    val rating: Double,
    val image_url: String?,
    val description: String?,
    val genres: List<String> = emptyList(),
)

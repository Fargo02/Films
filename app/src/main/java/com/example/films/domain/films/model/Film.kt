package com.example.films.domain.films.model

data class Film(
    val id: Long,
    val localizedName: String,
    val name: String,
    val year: String,
    val rating: Double,
    val imageUrl: String,
    val description: String,
    val genres: List<String>,
)

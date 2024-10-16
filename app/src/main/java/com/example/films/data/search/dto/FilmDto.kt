package com.example.films.data.search.dto

data class FilmDto(
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: String,
    val artworkUrl100: String,
    val trackId: Long,
    val collectionName: String? = null,
    val releaseDate: String?,
    val primaryGenreName: String,
    val country: String,
    val previewUrl: String? = null,
)

package com.example.films.ui.films.view_model

import com.example.films.domain.films.model.Film

sealed interface SearchState {
    data object Loading : SearchState

    data class Content(
        val films: List<Film>,
        val genres: List<String>,
    ) : SearchState

    data class Error(
        val lastSearch: String
    ) : SearchState

    data object Empty : SearchState
}
package com.example.films.domain.films

import com.example.films.domain.films.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmsInteractor {

    fun searchFilms(): Flow<Pair<List<Film>?, String?>>

}
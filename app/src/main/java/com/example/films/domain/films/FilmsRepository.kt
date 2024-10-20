package com.example.films.domain.films

import com.example.films.domain.films.model.Film
import com.example.films.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    fun searchFilms(): Flow<Resource<List<Film>>>

}
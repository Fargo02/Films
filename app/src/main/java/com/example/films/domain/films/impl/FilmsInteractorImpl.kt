package com.example.films.domain.films.impl

import com.example.films.domain.films.FilmsInteractor
import com.example.films.domain.films.FilmsRepository
import com.example.films.domain.films.model.Film
import com.example.films.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmsInteractorImpl(
    private val repository: FilmsRepository
): FilmsInteractor {

    override fun searchFilms(): Flow<Pair<List<Film>?, String?>> {
        return repository.searchFilms().map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(
                        result.data, null
                    )
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }

}
package com.example.films.ui.films.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.films.domain.films.FilmsInteractor
import com.example.films.domain.films.model.Film
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val filmsInteractor: FilmsInteractor
): ViewModel() {

    private val searchStateLiveData = MutableLiveData<SearchState>()
    fun observeSearchStateLiveData() : LiveData<SearchState> = searchStateLiveData

    init {
        getFilms()
    }

    fun getFilms(genre: String = "") {
        renderState(SearchState.Loading)
        viewModelScope.launch {
            delay(DELAY)
            filmsInteractor
                .searchFilms()
                .collect{ pair ->
                    processResult(pair.first, pair.second, genre)
                }
        }
    }

    private fun processResult(foundFilms: List<Film>?, errorMessage: String?, genre: String) {

        val films = mutableListOf<Film>()

        if (foundFilms != null) {
            if (genre != "") {
                films.addAll(foundFilms.filter { film ->
                    film.genres.contains(genre.lowercase())
                })
            } else {
                films.addAll(foundFilms)
            }
        }

        when {
            errorMessage != null -> {
                renderState(
                    SearchState.Error
                )
            }
            films.isEmpty() -> {
                renderState(
                    SearchState.Empty
                )
            }
            else -> {
                renderState(
                    SearchState.Content(
                        films = films,
                    )
                )
            }
        }
    }

    private fun renderState(state: SearchState) {
        searchStateLiveData.postValue(state)
    }

    companion object {
        private const val DELAY = 300L
    }
}
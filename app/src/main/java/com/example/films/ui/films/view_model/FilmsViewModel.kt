package com.example.films.ui.films.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.films.domain.films.FilmsInteractor
import com.example.films.domain.films.model.Film
import com.example.films.ui.mapper.getGenresMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val filmsInteractor: FilmsInteractor
): ViewModel() {

    private val searchStateLiveData = MutableLiveData<SearchState>()
    fun observeSearchStateLiveData() : LiveData<SearchState> = searchStateLiveData

    private val selectedGenres = MutableLiveData<Int>()
    fun observeSelectedGenres() : LiveData<Int> = selectedGenres

    init {
        getFilms()
        selectedGenres.postValue(selectedGenres.value ?: -1)
    }

    fun getGenres(genre: String, position: Int) {
        viewModelScope.launch {
            delay(DELAY)
            selectedGenres.postValue(position)
            getFilms(genre)
        }
    }

    fun getFilms(genre: String = "") {
        renderStateFilm(SearchState.Loading)
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
                renderStateFilm(
                    SearchState.Error(
                        lastSearch = genre
                    )
                )
            }
            films.isEmpty() -> {
                renderStateFilm(
                    SearchState.Empty
                )
            }
            else -> {
                renderStateFilm(
                    SearchState.Content(
                        films = films,
                        genres = getGenresMapper(foundFilms),
                    )
                )
            }
        }
    }

    private fun renderStateFilm(state: SearchState) {
        searchStateLiveData.postValue(state)
    }

    companion object {
        private const val DELAY = 300L
    }
}
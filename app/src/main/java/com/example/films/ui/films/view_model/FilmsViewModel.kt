package com.example.films.ui.films.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.films.domain.films.FilmsInteractor
import com.example.films.domain.films.model.Film
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val filmsInteractor: FilmsInteractor
): ViewModel() {

    init {
        viewModelScope.launch {
            Log.i("test","test_1")
            filmsInteractor
                .searchFilms()
                .collect{ pair ->
                    processResult(pair.first, pair.second)
                }
        }
    }

    fun test() {
        Log.i("test","test_2")
    }
    private fun processResult(foundFilms: List<Film>?, errorMessage: String?) {
        val films = mutableListOf<Film>()

        if (foundFilms != null) {
            films.addAll(foundFilms)
        }

//        when {
//            errorMessage != null -> {
//                renderState(
//                    SearchState.Error
//                )
//            }
//            tracks.isEmpty() -> {
//                renderState(
//                    SearchState.Empty
//                )
//            }
//            else -> {
//                renderState(
//                    SearchState.Content(
//                        tracks = tracks,
//                    )
//                )
//            }
//        }
    }
}
package com.practicum.mymovies.di


import com.example.films.ui.about.view_model.AboutViewModel
import com.example.films.ui.films.view_model.FilmsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        AboutViewModel()
    }
    viewModel {
        FilmsViewModel()
    }
}
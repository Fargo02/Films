package com.example.films.di

import com.example.films.domain.films.FilmsInteractor
import com.example.films.domain.films.impl.FilmsInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single <FilmsInteractor>{
        FilmsInteractorImpl(get())
    }
}
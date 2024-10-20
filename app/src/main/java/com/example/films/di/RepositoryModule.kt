package com.example.films.di

import com.example.films.data.search.FilmsRepositoryImpl
import com.example.films.domain.films.FilmsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single <FilmsRepository> {
        FilmsRepositoryImpl(get(), get())
    }
}
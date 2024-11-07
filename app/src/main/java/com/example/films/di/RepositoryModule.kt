package com.example.films.di

import com.example.films.data.search.FilmsRepositoryImpl
import com.example.films.domain.films.FilmsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModuleBinder {

    @Binds
    abstract fun bindFilmsRepository(
        filmsRepositoryImpl: FilmsRepositoryImpl
    ): FilmsRepository
}

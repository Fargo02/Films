package com.example.films.di

import com.example.films.domain.films.FilmsInteractor
import com.example.films.domain.films.impl.FilmsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModuleBinder {

    @Binds
    abstract fun bindFilmsInteractor(
        filmsInteractorImpl: FilmsInteractorImpl
    ): FilmsInteractor
}


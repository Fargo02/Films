package com.example.films.di

import com.example.films.domain.films.FilmsInteractor
import com.example.films.domain.films.FilmsRepository
import com.example.films.domain.films.impl.FilmsInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    @Singleton
    fun provideFilmsInteractor(repository: FilmsRepository): FilmsInteractor {
        return FilmsInteractorImpl(repository)
    }
}


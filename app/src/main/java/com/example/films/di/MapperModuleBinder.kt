package com.example.films.di

import com.example.films.data.mapper.FilmsResponseMapper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModuleProvider {

    @Provides
    fun provideFilmsResponseMapper(): FilmsResponseMapper {
        return FilmsResponseMapper()
    }
}
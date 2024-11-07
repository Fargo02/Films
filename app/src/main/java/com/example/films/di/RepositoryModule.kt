package com.example.films.di

import com.example.films.data.mapper.FilmsResponseMapper
import com.example.films.data.search.FilmsRepositoryImpl
import com.example.films.data.search.NetworkClient
import com.example.films.domain.films.FilmsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFilmsRepository(
        networkClient: NetworkClient,
        mapper: FilmsResponseMapper
    ): FilmsRepository {
        return FilmsRepositoryImpl(networkClient, mapper)
    }
}

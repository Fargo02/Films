package com.example.films.di

import android.content.Context
import com.example.films.data.mapper.FilmsResponseMapper
import com.example.films.data.search.NetworkClient
import com.example.films.data.search.network.ApiService
import com.example.films.data.search.network.RetrofitNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkClient(
        apiService: ApiService,
        @ApplicationContext context: Context
    ): NetworkClient {
        return RetrofitNetworkClient(apiService, context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideFilmsResponseMapper(): FilmsResponseMapper {
        return FilmsResponseMapper()
    }
}

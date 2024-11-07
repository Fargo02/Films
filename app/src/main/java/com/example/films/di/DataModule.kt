package com.example.films.di

import com.example.films.data.search.NetworkClient
import com.example.films.data.search.network.ApiService
import com.example.films.data.search.network.RetrofitNetworkClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModuleProvider {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkClientModuleBinder {

    @Binds
    abstract fun provideNetworkClient(
        retrofitNetworkClient: RetrofitNetworkClient
    ): NetworkClient
}



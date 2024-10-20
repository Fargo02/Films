package com.example.films.di

import com.example.films.data.mapper.FilmsResponseMapper
import com.example.films.data.search.NetworkClient
import com.example.films.data.search.network.ApiService
import com.example.films.data.search.network.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

    factory { FilmsResponseMapper() }

}
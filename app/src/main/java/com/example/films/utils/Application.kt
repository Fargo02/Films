package com.example.films.utils

import android.app.Application
import com.practicum.mymovies.di.dataModule
import com.practicum.mymovies.di.interactorModule
import com.practicum.mymovies.di.repositoryModule
import com.practicum.mymovies.di.viewModelModule
import org.koin.core.context.startKoin

class Application(): Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                dataModule,
                repositoryModule,
                interactorModule,
                viewModelModule,
            )
        }
    }
}
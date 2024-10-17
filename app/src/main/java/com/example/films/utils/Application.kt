package com.example.films.utils

import android.app.Application
import com.example.films.di.dataModule
import com.example.films.di.interactorModule
import com.example.films.di.repositoryModule
import com.example.films.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application(): Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                dataModule,
                repositoryModule,
                interactorModule,
                viewModelModule,
            )
        }
    }
}
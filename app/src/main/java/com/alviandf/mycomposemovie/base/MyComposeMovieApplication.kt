package com.alviandf.mycomposemovie.base

import android.app.Application
import com.alviandf.mycomposemovie.di.databaseModule
import com.alviandf.mycomposemovie.di.networkModule
import com.alviandf.mycomposemovie.di.repositoryModule
import com.alviandf.mycomposemovie.di.useCaseModule
import com.alviandf.mycomposemovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyComposeMovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyComposeMovieApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
package com.example.userlistapp.application

import android.app.Application
import com.example.userlistapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        initKoin()
    }


    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                repositoryModule,
                dataSourceModule,
                useCaseModule,
                viewModelModule,
            )
        }

    }
}
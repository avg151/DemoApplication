package com.example.demoapplication

import android.app.Application
import com.example.demoapplication.main.di.networkModules
import com.example.demoapplication.main.di.repositoryModules
import com.example.demoapplication.main.di.useCaseModules
import com.example.demoapplication.main.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModels +
                    repositoryModules +
                    useCaseModules +
                    networkModules
            )
        }
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
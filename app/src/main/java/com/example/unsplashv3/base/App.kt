package com.example.unsplashv3.base

import android.app.Application
import com.example.unsplashv3.data.di.appModule
import com.example.unsplashv3.data.di.dbModule
import com.example.unsplashv3.data.di.networkModule
import com.example.unsplashv3.data.di.viewModelModule
import io.ktor.util.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    @KtorExperimentalAPI
    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    @KtorExperimentalAPI
    private fun initDI() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(appModule, networkModule, dbModule, viewModelModule)
            )
        }
    }
}
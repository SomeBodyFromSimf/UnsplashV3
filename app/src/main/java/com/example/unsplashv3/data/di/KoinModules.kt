package com.example.unsplashv3.data.di

import androidx.room.Room
import com.example.unsplashv3.data.db.AppDataBase
import com.example.unsplashv3.data.network.RequestManager
import com.example.unsplashv3.data.repo.MainRepository
import com.example.unsplashv3.data.repo.Repository
import com.example.unsplashv3.ui.screens.detail.DetailVM
import com.example.unsplashv3.ui.screens.main.favorite.FavoriteVM
import com.example.unsplashv3.ui.screens.main.photos.PhotoVM
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.util.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    single { Gson() }
    single<Repository> { MainRepository(get(),get()) }
}

@KtorExperimentalAPI
val networkModule = module {
    single {
        HttpClient(CIO) {
            install(DefaultRequest) {
                headers.append("Content-Type", "application/json")
            }
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
    }
    single { RequestManager(get()) }
}

val dbModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDataBase>().photoDao() }
}

val viewModelModule = module {
    viewModel { FavoriteVM() }
    viewModel { PhotoVM() }
    viewModel { DetailVM() }
}
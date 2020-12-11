package com.example.unsplashv3.data.network

import com.example.unsplashv3.model.Photo
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class RequestManager(private val client: HttpClient) {

    suspend fun getPhotos(page: Int): List<Photo?> =
        client.get {url("https://api.unsplash.com/photos/?client_id=ff212ebf462c479256f914b33b8361a9860bb12b8d56b0800649a3040a37683c&page=$page")}

    suspend fun getPhoto(photoId: String): Photo =
        client.get { url("https://api.unsplash.com/photos/$photoId/?client_id=ff212ebf462c479256f914b33b8361a9860bb12b8d56b0800649a3040a37683c") }
}
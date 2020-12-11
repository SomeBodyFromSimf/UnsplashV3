package com.example.unsplashv3.data.repo

import com.example.unsplashv3.model.Photo
import com.example.unsplashv3.model.PhotoWithLike
import com.example.unsplashv3.utils.DataState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun getPhotos(page: Int): List<Photo>
    suspend fun getPhoto(photoId: String?): Flow<Photo?>

    fun getPhotosFromDb(): Flow<List<Photo>>
    fun checkInBD(photoId: String): Flow<Boolean>
    suspend fun addToDB(photo: Photo)
    suspend fun removeFromDB(photo: Photo)
}

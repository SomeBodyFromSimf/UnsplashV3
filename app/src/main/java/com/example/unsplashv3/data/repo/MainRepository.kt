package com.example.unsplashv3.data.repo

import com.example.unsplashv3.data.db.PhotoDao
import com.example.unsplashv3.data.db.mapToDBEntity
import com.example.unsplashv3.data.db.mapToPhotoList
import com.example.unsplashv3.data.network.RequestManager
import com.example.unsplashv3.model.Photo
import com.example.unsplashv3.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MainRepository(
    private val photoDao: PhotoDao,
    private val networkManager: RequestManager
) : Repository {

    override suspend fun getPhotos(page: Int): List<Photo> =
        networkManager.getPhotos(page).filterNotNull()

    override suspend fun getPhoto(photoId: String?): Flow<Photo?> = flow {
        emit(null)
        if(photoId != null) emit(networkManager.getPhoto(photoId))
    }.catch { e-> e.printStackTrace() }

    override fun getPhotosFromDb(): Flow<List<Photo>> =
        photoDao.getPhotosFlow().map { it.mapToPhotoList() }.catch { e-> e.printStackTrace() }.flowOn(Dispatchers.Default)
            .conflate().distinctUntilChanged()

    override fun checkInBD(photoId: String): Flow<Boolean> =
        photoDao.checkById(photoId).map { it == 1 }.catch { e-> e.printStackTrace() }


    override suspend fun addToDB(photo: Photo) {
        photoDao.addPhoto(photo.mapToDBEntity())
    }

    override suspend fun removeFromDB(photo: Photo) {
        photoDao.deletePhoto(photo.mapToDBEntity())
    }
}
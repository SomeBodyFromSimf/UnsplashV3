package com.example.unsplashv3.data.db

import androidx.room.*
import com.example.unsplashv3.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM unsplash_fav_photos")
    fun getPhotosFlow(): Flow<List<PhotoDBEntity>>

    @Query("SELECT COUNT(*) FROM unsplash_fav_photos WHERE id = :photoId  LIMIT 1")
    fun checkById(photoId: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhoto(photo: PhotoDBEntity)

    @Delete
    suspend fun deletePhoto(photo: PhotoDBEntity)
}
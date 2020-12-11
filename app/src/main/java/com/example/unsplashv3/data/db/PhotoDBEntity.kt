package com.example.unsplashv3.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unsplashv3.model.Photo
import kotlinx.parcelize.Parcelize


@Entity(tableName = "unsplash_fav_photos")
@Parcelize
data class PhotoDBEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "description")
    var description: String?,

    @Embedded
    var urls: PhotoUrl
) : Parcelable {
    @Parcelize
    data class PhotoUrl(
        @ColumnInfo(name = "full_link")
        var full: String,

        @ColumnInfo(name = "small_link")
        var small: String,

        @ColumnInfo(name = "thumb_link")
        var thumb: String
    ) : Parcelable
}

fun Photo.mapToDBEntity(): PhotoDBEntity {
    return PhotoDBEntity(
        id = id,
        description = description,
        urls = PhotoDBEntity.PhotoUrl(
            full = urls.full,
            small = urls.small,
            thumb = urls.thumb
        )
    )
}

fun PhotoDBEntity.mapToPhoto(): Photo {
    return Photo(
        id = id,
        description = description,
        urls = Photo.PhotoUrl(
            full = urls.full,
            small = urls.small,
            thumb = urls.thumb
        )
    )
}

fun List<PhotoDBEntity>.mapToPhotoList(): List<Photo> {
    return map { it.mapToPhoto() }
}
package com.example.unsplashv3.model

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    var id: String,
    var description: String?,
    var urls: PhotoUrl
) : Parcelable {
    @Parcelize
    data class PhotoUrl(
        var full: String,
        var small: String,
        var thumb: String
    ) : Parcelable
}

@Parcelize
data class PhotoWithLike(
    val photo: Photo,
    var like: Boolean
) : Parcelable {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
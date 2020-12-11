package com.example.unsplashv3.ui.screens.main.favorite

import androidx.lifecycle.asLiveData
import com.example.unsplashv3.base.BaseVM
import com.example.unsplashv3.model.PhotoWithLike
import kotlinx.coroutines.flow.map

class FavoriteVM : BaseVM() {

    val photos = repository.getPhotosFromDb().map { it.map { photo -> PhotoWithLike(photo,true) } }.asLiveData()

}
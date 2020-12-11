package com.example.unsplashv3.ui.screens.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.unsplashv3.R

sealed class LocalRouter(val route: String, @StringRes val resourceId: Int, @DrawableRes val icon: Int){
    object Favorites : LocalRouter("Favorites", R.string.favorites, R.drawable.ic_favorite)
    object Photos : LocalRouter("Photos", R.string.photos, R.drawable.ic_photo)
    object Info : LocalRouter("Info", R.string.info, R.drawable.ic_info)
}
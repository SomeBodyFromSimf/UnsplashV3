package com.example.unsplashv3.ui.screens.main.photos

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.unsplashv3.base.BaseVM
import com.example.unsplashv3.data.paging.PhotoSource
import com.example.unsplashv3.model.Photo
import com.example.unsplashv3.model.PhotoWithLike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class PhotoVM : BaseVM() {

    private val netPhotos: Flow<PagingData<Photo>> =
        Pager(PagingConfig(pageSize = 25, initialLoadSize = 25)) { PhotoSource(repository) }.flow.cachedIn(viewModelScope)
    private val dbPhotos: Flow<List<Photo>> = repository.getPhotosFromDb()


    val photos: Flow<PagingData<PhotoWithLike>> = combine(netPhotos, dbPhotos) {p1,p2 ->
        p1.map { PhotoWithLike(it, p2.map(Photo::id).contains(it.id)) }
    }
}
package com.example.unsplashv3.ui.screens.detail

import androidx.lifecycle.*
import com.example.unsplashv3.base.BaseVM
import com.example.unsplashv3.model.Photo
import com.example.unsplashv3.model.PhotoWithLike
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.launch

class DetailVM : BaseVM() {

    private val error: MutableLiveData<String> = MutableLiveData("")
    val errorMessage: LiveData<String> = error


    val photoId: MutableLiveData<String?> = MutableLiveData(null)

    private val netPhotos: Flow<Photo?> = photoId.asFlow().flatMapLatest {
        repository.getPhoto(it)
    }

    private val isInDB: Flow<Boolean> = photoId.asFlow().flatMapLatest {
        if(it != null)
            repository.checkInBD(it)
        else emptyFlow()
    }

    val currentPhoto: LiveData<PhotoWithLike?> = combine(netPhotos,isInDB) { photo, isIn ->
        photo?.let{PhotoWithLike(photo,isIn)}
    }.asLiveData()

}
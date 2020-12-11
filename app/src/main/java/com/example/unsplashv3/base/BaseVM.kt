package com.example.unsplashv3.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashv3.data.repo.Repository
import com.example.unsplashv3.model.PhotoWithLike
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext

open class BaseVM: ViewModel() {
    private val koin = GlobalContext.get()
    protected val repository = koin.get<Repository>()

    private var isNeedWait = false

    fun onHeartClick(item : PhotoWithLike) = with(repository){
        if(isNeedWait) return@with
        viewModelScope.launch {
            isNeedWait = true
            if(item.like) removeFromDB(item.photo)
            else addToDB(item.photo)
            isNeedWait = false
        }
    }
}
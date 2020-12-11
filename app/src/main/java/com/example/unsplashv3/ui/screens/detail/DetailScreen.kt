package com.example.unsplashv3.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.example.unsplashv3.R
import com.example.unsplashv3.ui.components.ErrorView
import com.example.unsplashv3.ui.components.LoadingView
import com.example.unsplashv3.ui.components.NetworkImage

@Composable
fun DetailScreen(photoId : String, viewModel: DetailVM = viewModel()) {

    onCommit(photoId){viewModel.photoId.value = photoId}
    val photo = viewModel.currentPhoto.observeAsState()
    val errorMessage = viewModel.errorMessage.observeAsState("")

    photo.value?.let {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (image, heart) = createRefs()
            NetworkImage(url = it.photo.urls.full, modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, circularRevealedEnabled = true)
            IconButton(onClick = { viewModel.onHeartClick(it) },modifier = Modifier.constrainAs(heart) {
                bottom.linkTo(parent.bottom, 36.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.scale(4f)) {
                Icon(
                    vectorResource(id = R.drawable.ic_favorite),
                    tint = if (it.like) Color.Red else Color.White)
            }
        }
    } ?: run {
        if(errorMessage.value.isNotEmpty())
            ErrorView(error = errorMessage.value, onRefresh = {
                viewModel.photoId.postValue(null)
                viewModel.photoId.postValue(photoId)
            })
        else LoadingView()

    }
}
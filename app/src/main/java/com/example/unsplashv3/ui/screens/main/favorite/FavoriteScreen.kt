package com.example.unsplashv3.ui.screens.main.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.unsplashv3.ui.components.UnsplashList
import com.example.unsplashv3.ui.screens.Graph

@Composable
fun FavoriteScreen(mainRouter : NavController, viewModel: FavoriteVM = viewModel()) {
    val photos = viewModel.photos.observeAsState()
    photos.value?.let {
        UnsplashList(list = it, onHeartClick = viewModel::onHeartClick,
            onPhotoClick = {id -> mainRouter.navigate(Graph.Detail.route+"/"+id)}
        )
    }
}
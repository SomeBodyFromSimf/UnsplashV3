package com.example.unsplashv3.ui.screens.main.photos

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.navigation.compose.navigate
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.unsplashv3.model.PhotoWithLike
import com.example.unsplashv3.ui.screens.Graph
import com.example.unsplashv3.ui.components.*
import com.example.unsplashv3.ui.components.ErrorAppendView

@Composable
fun PhotoScreen(mainRouter: NavController, viewModel: PhotoVM = viewModel()) {
    val photos: LazyPagingItems<PhotoWithLike> = viewModel.photos.collectAsLazyPagingItems()

    //SwipeToRefreshBox(onRefresh = {photos.refresh()}) {
    when (val refreshState = photos.loadState.refresh) {
        is LoadState.Loading -> LoadingView()
        is LoadState.Error -> ErrorView(error = refreshState.error.toString(), onRefresh = {photos.refresh()})
        else -> {
            mutableListOf<PhotoWithLike>().apply {
                (0 until photos.itemCount).forEach { index ->
                    photos[index]?.let { add(it) }
                }
            }.toList().let {
                UnsplashList(list = it,
                    onHeartClick = viewModel::onHeartClick,
                    onPhotoClick = { id -> mainRouter.navigate(Graph.Detail.route + "/" + id) },
                    footer = {
                        when (val state = photos.loadState.append) {
                            is LoadState.Loading -> {
                                item {CircularProgressIndicator(modifier = Modifier.fillMaxWidth().wrapContentWidth()) }
                            }
                            is LoadState.Error -> {
                                item { ErrorAppendView(error = state.error.toString(), onRetry = {photos.retry()}) }
                            }
                        }
                    }
                )
            }
        }
    }
    //}
}

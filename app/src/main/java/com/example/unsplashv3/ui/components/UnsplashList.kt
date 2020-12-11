package com.example.unsplashv3.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.unit.dp
import com.example.unsplashv3.model.PhotoWithLike

@Composable
fun UnsplashList(list: List<PhotoWithLike>,onHeartClick: (PhotoWithLike) -> Unit, onPhotoClick: (String) -> Unit, footer: (LazyListScope.(LazyListState) -> Unit)? = null) {
    val configuration = AmbientConfiguration.current
    val state: LazyListState = rememberLazyListState()

    LazyGridFor(items = list, rowSize = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 4
            else -> 2
        }, state = state,
        content = { item -> ImageCard(item = item, onHeartClick = onHeartClick, onPhotoClick = onPhotoClick)},
        footer = footer ?: {}
    )
}
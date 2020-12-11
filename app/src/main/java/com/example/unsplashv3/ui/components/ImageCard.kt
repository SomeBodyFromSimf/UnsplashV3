package com.example.unsplashv3.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.unsplashv3.model.PhotoWithLike
import com.example.unsplashv3.R
import com.google.gson.Gson

@Composable
fun ImageCard(item : PhotoWithLike, onHeartClick : (PhotoWithLike) -> Unit, onPhotoClick : (String) -> Unit) {
    val configuration = AmbientConfiguration.current
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp,
        border = BorderStroke(2.dp, SolidColor(if (isSystemInDarkTheme()) Color.White else Color.Black)),
        modifier = Modifier.fillMaxWidth().aspectRatio(
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> 4 / 3f
                else -> 3 / 4f
            }
        ).padding(8.dp).clip(RoundedCornerShape(10.dp)).clickable(
            onClick = { onPhotoClick(item.photo.id) }
        )
    ) {
        ConstraintLayout {
            val (image, heart) = createRefs()
            NetworkImage(url = item.photo.urls.thumb, modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            IconButton(onClick = {
                onHeartClick(item)
            },modifier = Modifier.constrainAs(heart) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }) {
                Icon(
                    vectorResource(id = R.drawable.ic_favorite),
                    tint = if (item.like) Color.Red else Color.White)
            }
        }
    }
}
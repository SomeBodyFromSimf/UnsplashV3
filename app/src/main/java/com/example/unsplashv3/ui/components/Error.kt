package com.example.unsplashv3.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.unsplashv3.R
import com.example.unsplashv3.theme.typography
import com.example.unsplashv3.utils.lambda

@Composable
fun ErrorAppendView(error: String, onRetry: lambda) {
    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            imageResource(id = R.drawable.img_error),
            modifier = Modifier.height(35.dp),
            tint = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Ошибка, а именно :", style = typography.body2)
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = error)
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageResource(id = R.drawable.reload_2444925_2075802),
            modifier = Modifier.height(35.dp).clickable(onClick = onRetry),
            tint = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
    }
}

@Composable
fun ErrorView(error: String, onRefresh: lambda) {
    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ошибка, а именно :", style = typography.body2)
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = error)
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageResource(id = R.drawable.reload_2444925_2075802),
            modifier = Modifier.height(35.dp).clickable(onClick = onRefresh),
            tint = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
    }
}
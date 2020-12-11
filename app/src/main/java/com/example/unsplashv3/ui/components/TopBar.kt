package com.example.unsplashv3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.unsplashv3.utils.ComposableView
import com.example.unsplashv3.utils.MenuItem

@Composable
fun TopBar(
    title: String,
    navigationIcon: ComposableView? = null,
    actions: List<MenuItem> = listOf()
) {
    TopAppBar {
        navigationIcon?.let {
            Row(
                Modifier.fillMaxHeight().preferredWidth(68.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Providers(AmbientContentAlpha provides ContentAlpha.high,content = it)
            }
        } ?: Spacer(Modifier.preferredWidth(12.dp))
        Row(
            Modifier.fillMaxHeight().weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.h6) {
                Providers(AmbientContentAlpha provides ContentAlpha.high){
                    Text(title)
                }
            }
        }
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Row(
                Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = {actions.forEach {
                    IconButton(onClick = it.block) { Image(it.image) }
                }}
            )
        }
    }
}

@Preview
@Composable
fun ShowTopBar(title: String = "Здрасьте") {
    TopBar(title = title)
}
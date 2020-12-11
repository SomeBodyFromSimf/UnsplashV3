package com.example.unsplashv3.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview
import com.example.unsplashv3.ui.screens.Graph
import com.example.unsplashv3.R
import kotlinx.coroutines.*

@Preview(showBackground = true,showDecoration = true)
@Composable
fun SplashScreen(router : NavController? =null) {

    onCommit {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            router?.navigate(Graph.Main.route) {
                launchSingleTop = true
                popUpTo(router.currentDestination?.id ?: 0){inclusive=true}
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            vectorResource(id = R.drawable.ic_unsplash_logo),
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) Color.White else Color.Black)
        )
    }
}


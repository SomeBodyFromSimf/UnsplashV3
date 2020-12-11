package com.example.unsplashv3.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.unsplashv3.R
import com.example.unsplashv3.ui.components.TopBar
import com.example.unsplashv3.ui.screens.main.favorite.FavoriteScreen
import com.example.unsplashv3.ui.screens.main.photos.PhotoScreen
import com.example.unsplashv3.ui.screens.main.info.InfoScreen

@Composable
fun MainScreen(router : NavController) {
    val localRouter = rememberNavController()

    val items = listOf(
        LocalRouter.Favorites,
        LocalRouter.Photos,
        LocalRouter.Info
    )
    val navBackStackEntry by localRouter.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
    Scaffold(
        topBar = {
            TopBar(title = stringResource(when {
                    currentRoute?.equals(LocalRouter.Favorites.route) == true -> R.string.favorites
                    currentRoute?.equals(LocalRouter.Photos.route) == true -> R.string.photos
                    currentRoute?.equals(LocalRouter.Info.route) == true -> R.string.info
                    else -> R.string.photos
            }))
        },
        bottomBar = {
            BottomNavigation {
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Image(imageVector = vectorResource(id = screen.icon)) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                localRouter.popBackStack(localRouter.graph.startDestination, false)
                                if(screen.route != LocalRouter.Photos.route)
                                    localRouter.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            NavHost(localRouter, startDestination = LocalRouter.Photos.route) {
                composable(LocalRouter.Favorites.route) { FavoriteScreen(router) }
                composable(LocalRouter.Photos.route) { PhotoScreen(router) }
                composable(LocalRouter.Info.route) { InfoScreen() }
            }
        }
    }
}
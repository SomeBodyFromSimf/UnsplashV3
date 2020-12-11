package com.example.unsplashv3.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.unsplashv3.ui.screens.detail.DetailScreen
import com.example.unsplashv3.ui.screens.main.MainScreen
import com.example.unsplashv3.ui.screens.splash.SplashScreen

@Composable
fun MainRouter() {
    val router = rememberNavController()
    NavHost(router, startDestination = Graph.Splash.route) {
        composable(Graph.Splash.route) { SplashScreen(router) }
        composable(Graph.Main.route) { MainScreen(router) }
        composable("${Graph.Detail.route}/{photoUrl}",
            arguments = listOf(navArgument("photoUrl") { type = NavType.StringType })
        ) {
            it.arguments?.getString("photoUrl")?.let{ url -> DetailScreen(url) }
        }
    }
}
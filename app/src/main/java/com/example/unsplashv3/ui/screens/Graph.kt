package com.example.unsplashv3.ui.screens

sealed class Graph(val route: String) {
    object Splash: Graph("Splash")
    object Main: Graph("Main")
    object Detail: Graph("Splash")
}
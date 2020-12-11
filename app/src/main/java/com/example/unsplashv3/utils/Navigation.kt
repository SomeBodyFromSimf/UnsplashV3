package com.example.unsplashv3.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.unsplashv3.R


val defaultNavBuilder: NavOptions.Builder
    get() = NavOptions.Builder()
        .setEnterAnim(R.anim.activity_in)
        .setExitAnim(R.anim.activity_out)
        .setPopEnterAnim(R.anim.activity_back_in)
        .setPopExitAnim(R.anim.activity_back_out)
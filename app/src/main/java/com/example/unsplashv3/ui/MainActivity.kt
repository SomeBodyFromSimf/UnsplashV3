package com.example.unsplashv3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import com.example.unsplashv3.theme.UnsplashV3Theme
import com.example.unsplashv3.ui.screens.MainRouter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashV3Theme {
                Surface(color = if (isSystemInDarkTheme()) MaterialTheme.colors.background else Color(0xFFF2F2F2)) {
                    MainRouter()
                }
            }
        }
    }
}
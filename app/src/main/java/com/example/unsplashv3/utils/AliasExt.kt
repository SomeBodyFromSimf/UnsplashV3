package com.example.unsplashv3.utils

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

typealias ComposableView = @Composable () -> Unit
typealias lambda = () -> Unit
typealias typeLambda<T> = (T) -> Unit

data class MenuItem (val str: String, val image: ImageVector, val block: lambda)

fun Int.toPx(): Int = (toFloat() * Resources.getSystem().displayMetrics.density).toInt()

package com.example.appmobile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.PeriodicWorkRequestBuilder
import com.example.appmobile.ui.screens.AppScreen
import com.example.appmobile.ui.themes.MangaAppTheme
import com.example.appmobile.work.RefreshMangaWorker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MangaAppTheme {
                SplashActivity()
                AppScreen()
            }
        }
    }
}


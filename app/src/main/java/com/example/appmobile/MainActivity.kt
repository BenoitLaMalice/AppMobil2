package com.example.appmobile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appmobile.ui.screens.AppScreen
import com.example.appmobile.ui.themes.MangaAppTheme
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

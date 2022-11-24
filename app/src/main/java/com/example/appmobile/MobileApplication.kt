package com.example.appmobile

import android.app.Application
import com.example.appmobile.data.AppContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileApplication: Application() {
    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container=AppContainer.DefaultAppContainer()
    }


}
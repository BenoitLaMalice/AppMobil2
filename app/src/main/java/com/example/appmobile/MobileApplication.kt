package com.example.appmobile

import android.app.Application
import android.content.Context
import com.example.appmobile.data.AppContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileApplication: Application() {

    companion object{
        private var sApplication : Application?=null
        fun getApplication():Application?{
            return sApplication
        }
        fun getContext(): Context? {
            return getApplication()!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
        sApplication=this
    }


}
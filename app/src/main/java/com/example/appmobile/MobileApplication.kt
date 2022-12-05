package com.example.appmobile

import android.app.Application
import android.content.Context
import com.example.appmobile.data.AppContainer
import com.example.appmobile.data.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileApplication: Application() {
    val database : AppDatabase by lazy {AppDatabase.getDatabase(this)}
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
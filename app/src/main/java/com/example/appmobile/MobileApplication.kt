package com.example.appmobile

import android.app.Application
import android.content.Context
import androidx.work.*
import com.example.appmobile.data.AppContainer
import com.example.appmobile.data.AppDatabase
import com.example.appmobile.work.RefreshMangaWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


@HiltAndroidApp
class MobileApplication: Application() {
    val database : AppDatabase by lazy {AppDatabase.getDatabase(this)}
    private val applicationScope = CoroutineScope(Dispatchers.Default)
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
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshMangaWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshMangaWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}





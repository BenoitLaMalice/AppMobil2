package com.example.appmobile.work

import android.content.Context

import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.appmobile.data.sources.MangaCacheSource

class RefreshMangaWorker(appContext: Context,params: WorkerParameters) : CoroutineWorker(appContext,params){
    override suspend fun doWork(): Result {
        try {
            MangaCacheSource.refreshManga()
        }catch (e:Exception){
            return Result.retry()
        }
        return Result.success()

    }

    companion object {
        const val WORK_NAME = "com.example.appmobile.work.RefreshMangaWorker"
    }
}
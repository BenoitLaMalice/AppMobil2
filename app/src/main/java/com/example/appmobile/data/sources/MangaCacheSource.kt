package com.example.appmobile.data.sources

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.appmobile.MobileApplication
import com.example.appmobile.data.AppDatabase
import com.example.appmobile.data.models.MangaModel
import com.example.appmobile.data.repositories.DefaultMangaRepositoryEntryPoint
import com.example.appmobile.data.repositories.MangaSource
import com.example.appmobile.work.RefreshMangaWorker
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.concurrent.TimeUnit


@Dao
interface MangaDao{
    @Query("SELECT * FROM mangas")
    fun getAll(): Flow<MangaModel>

    @Query("SELECT * FROM mangas WHERE title = :mangaName")
    fun getByName(mangaName : String):Flow<MangaModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(manga : MangaModel?)
}

object MangaCacheSource : MangaSource {
    private lateinit var  appDatabase : AppDatabase

    suspend fun refreshManga(){
        withContext(Dispatchers.IO){
            val manga = OnlineMangaSources.GetRandom()
            appDatabase.mangaDao().insert(manga)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private var lastUpdateTime:Instant = Instant.now()

    init {
        val appContext = MobileApplication.getContext()
        val utilitiesEntryPoint = appContext?.let{
            EntryPointAccessors.fromApplication(it,DefaultMangaCacheSourceEntryPoint::class.java)
        }
        appDatabase = utilitiesEntryPoint?.appDatabase!!


    //Update le cache
    val repeatingRequest = PeriodicWorkRequestBuilder<RefreshMangaWorker>(1,TimeUnit.HOURS).build()
    WorkManager.getInstance(MobileApplication.getContext()!!).enqueue(repeatingRequest)
    }
    override suspend fun GetRandom(): Flow<MangaModel> {
        refreshManga()
        return appDatabase.mangaDao().getAll()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultMangaCacheSourceEntryPoint{
    var appDatabase : AppDatabase
}
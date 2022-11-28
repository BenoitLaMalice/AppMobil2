package com.example.appmobile.data.repositories

import com.example.appmobile.MobileApplication
import com.example.appmobile.data.models.MangaModel
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


interface MangaSource{
    suspend fun GetRandom(): MangaModel
}

interface  MangaRepository{
    suspend fun GetRandomManga(): MangaModel
}

class DefaultMangaRepository @Inject constructor() : MangaRepository{
    private lateinit var mangaSource : MangaSource
    init {
        val appContext = MobileApplication.getContext()
        val utilitiesEntryPoint = appContext?.let { EntryPointAccessors.fromApplication(it,DefaultMangaRepositoryEntryPoint::class.java) }
        mangaSource=utilitiesEntryPoint?.mangaSource!!
    }

    override suspend fun  GetRandomManga(): MangaModel {
        return mangaSource.GetRandom()
    }

}
@InstallIn(SingletonComponent::class)
@Module
object MangaRepositoryModule{
    @Provides
    fun provideMangaRepo():MangaRepository{
        return DefaultMangaRepository()
    }
    
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultMangaRepositoryEntryPoint {
    var mangaSource: MangaSource
}
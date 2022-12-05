package com.example.appmobile.data.repositories

import com.example.appmobile.MobileApplication
import com.example.appmobile.data.models.MangaModel
import com.example.appmobile.data.sources.MangaCacheSource
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


interface MangaSource{
    suspend fun GetRandom(): Flow<MangaModel>
}

interface  MangaRepository{
    suspend fun GetRandomManga(): Flow<MangaModel>
}

class DefaultMangaRepository @Inject constructor() : MangaRepository{
    private lateinit var mangaSource : MangaSource
    init {
        val appContext = MobileApplication.getContext()
        val utilitiesEntryPoint = appContext?.let { EntryPointAccessors.fromApplication(it,DefaultMangaRepositoryEntryPoint::class.java) }
        mangaSource=utilitiesEntryPoint?.mangaSource!!
    }

    override suspend fun  GetRandomManga(): Flow<MangaModel> {
        return mangaSource.GetRandom()
    }

}
@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultMangaRepositoryEntryPoint {
    var mangaSource: MangaSource
}

@InstallIn(SingletonComponent::class)
@Module
object MangaRepositoryModule{
    @Provides
    fun provideMangaRepo():MangaRepository{
        return DefaultMangaRepository()
    }
    
}
@InstallIn(SingletonComponent::class)
@Module
object MangaSourceModule {
    @Provides
    @Singleton
    fun provideGameSource(): MangaSource {
        return MangaCacheSource
    }
}



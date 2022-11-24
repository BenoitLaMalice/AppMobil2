package com.example.appmobile.data.repositories

import com.example.appmobile.data.models.MangaModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


interface MangaSource{
    suspend fun GetRandom(): MangaModel
}

interface  MangaRepository{
    suspend fun GetRandomManga(): MangaModel
}

class DefaultMangaRepository() : MangaRepository{
    @Inject
    lateinit var mangaSource:MangaSource
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
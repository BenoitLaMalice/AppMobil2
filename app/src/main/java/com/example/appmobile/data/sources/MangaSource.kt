package com.example.appmobile.data.sources

import com.example.appmobile.data.models.MangaModel
import com.example.appmobile.data.repositories.DefaultMangaRepository
import com.example.appmobile.data.repositories.MangaRepository
import com.example.appmobile.data.repositories.MangaSource
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


object OnlineMangaSources : MangaSource {
    private const val BASE_URL = "https://api.jikan.moe"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


    data class JikanRandomManga(
        @Json(name = "data")
        val data: Manga
    )

    data class Manga(
        @Json(name="mal_id")
        val id : Int,

        @Json(name="title")
        val name : String,

        @Json(name="volumes")
        val tome : Int

    )

    interface JikanMangaService{
        @GET("/v4/random/manga")
        fun GetRandomManga() : JikanRandomManga
    }

    private val retrofitJikanMangaService:JikanMangaService by lazy{
        retrofit.create(JikanMangaService::class.java)
    }

    override suspend fun GetRandomManga(): MangaModel {
        val nande =retrofitJikanMangaService.GetRandomManga()
            .data

        return MangaModel(nande.id,nande.name,nande.tome)




    }

}

@InstallIn(SingletonComponent::class)
@Module
object MangaSourceModule{
    @Provides
    fun provideMangaSource(): MangaSource {
        return OnlineMangaSources
    }
}
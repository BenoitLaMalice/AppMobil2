package com.example.appmobile.data.sources


import android.util.Log
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
import javax.inject.Singleton


object OnlineMangaSources  {
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
        val data: MangaModel
    )

    interface JikanMangaService{
        @GET("v4/random/manga")
        suspend fun GetRandomManga() : JikanRandomManga
    }

    private val retrofitJikanMangaService:JikanMangaService by lazy{
        retrofit.create(JikanMangaService::class.java)
    }

    suspend fun GetRandom(): MangaModel {
        val data=retrofitJikanMangaService.GetRandomManga()
            .data
        if (data.tome==null){
            return MangaModel(data.mal_id,data.name,0)
        }
        //data.id
        return MangaModel(data.mal_id,data.name,data.tome)




    }

}


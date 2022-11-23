package com.example.appmobile.data.sources;

import com.example.appmobile.data.models.MangaModel;
import com.example.appmobile.data.repositories.MangaSource;
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object OnlineMangaSources : MangaSource {
    private const val BASE_URL = "https://api.jikan.moe/v4/random/manga"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    
    data class JikanManga{

    }

    override fun GetMangas(): List<MangaModel> {
        TODO("Not yet implemented")
    }

}
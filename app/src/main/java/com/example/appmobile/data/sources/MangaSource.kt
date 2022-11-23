package com.example.appmobile.data.sources;

import com.example.appmobile.data.models.MangaModel;
import com.example.appmobile.data.repositories.MangaSource;
import retrofit2.Retrofit

object OnlineMangaSources : MangaSource {
    private const val BASE_URL = "https://api.jikan.moe/v4/random/manga"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)

        .build()


    override fun GetMangas(): List<MangaModel> {
        TODO("Not yet implemented")
    }

}
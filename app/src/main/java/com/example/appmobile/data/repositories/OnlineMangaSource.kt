package com.example.appmobile.data.repositories

import com.example.appmobile.data.models.MangaModel


interface MangaSource{
    fun GetMangas(): List<MangaModel>
}

interface  MangaRepository{
    fun GetMangas(): List<MangaModel>
    fun GetMangasFromCache(): List<MangaModel>
}

class DefaultMangaRepository(val mangaSource : MangaSource) : MangaRepository{
    override fun GetMangas(): List<MangaModel> {
        TODO("Not yet implemented")
    }

    override fun GetMangasFromCache(): List<MangaModel> {
        TODO("Not yet implemented")
    }

}
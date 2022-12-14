package com.example.appmobile.data

import com.example.appmobile.data.repositories.DefaultMangaRepository
import com.example.appmobile.data.repositories.MangaRepository
import com.example.appmobile.data.repositories.MangaSource
import com.example.appmobile.data.sources.OnlineMangaSources

interface AppContainer {
    val mangaSource : MangaSource
    val mangaRepository : MangaRepository




}
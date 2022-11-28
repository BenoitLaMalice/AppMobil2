package com.example.appmobile.data.models

import com.squareup.moshi.Json

data class MangaModel(
    @field:Json(name="mal_id")
    val id : Int,

    @field:Json(name="title")
    val name : String,

    @field:Json(name="volumes")
    val tome : Int

)
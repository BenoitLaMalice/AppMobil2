package com.example.appmobile.data.models

import com.squareup.moshi.Json

data class MangaModel(
    @Json(name="mal_id")
    val id : Int,

    @Json(name="title")
    val name : String,

    @Json(name="volumes")
    val tome : Int

)
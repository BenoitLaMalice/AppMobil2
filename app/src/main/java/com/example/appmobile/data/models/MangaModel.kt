package com.example.appmobile.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "mangas")
data class MangaModel(



    @NonNull@PrimaryKey @ColumnInfo(name="mal_id")
    @Json(name="mal_id")
    val mal_id : Int,

    @NonNull @ColumnInfo(name="title")
    @Json(name="title")
    val name : String,

    @ColumnInfo(name="volumes")
    @Json(name="volumes")
    val volumes : Int

)
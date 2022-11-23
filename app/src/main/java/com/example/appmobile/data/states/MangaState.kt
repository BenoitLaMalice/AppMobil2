package com.example.appmobile.data.states

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*


data class MangaState @RequiresApi(Build.VERSION_CODES.O) constructor(
    val name:String="",
    val tome:String="",
    val date:String="",
)
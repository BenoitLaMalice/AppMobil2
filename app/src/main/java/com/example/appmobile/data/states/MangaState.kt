package com.example.appmobile.data.states




data class MangaState (val name:String = "",val tome:Int =0 )

fun getName(mangaState:MangaState): String {
    return mangaState.name
}

fun getTome(mangaState:MangaState): Int {
    return mangaState.tome
}
    //val date:String="",

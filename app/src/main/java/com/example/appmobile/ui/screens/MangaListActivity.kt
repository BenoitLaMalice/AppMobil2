package com.example.appmobile.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.appmobile.R

enum class RandomMangaRoutes(@StringRes val title:Int){
    RandomManga(R.string.random_manga)
}

fun NavGraphBuilder.HomeNavigation(modifier: Modifier= Modifier,
                                   navController: NavController,

                                   onTitleChanged : (Int) -> Unit,
                                   onCanNavigateBackChange:(Boolean)->Unit
){
    navigation(startDestination = RandomMangaRoutes.RandomManga.name,route = Screen.MangaList.route, ){
        composable(route = RandomMangaRoutes.RandomManga.name){
            onCanNavigateBackChange(true)
            RandomMangaScreen(modifier)


        }
    }
}

@Composable
fun MangaList(navController: NavController,modifier: Modifier = Modifier){
    Column (modifier = modifier.fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Mangatest()
    }
}


@Composable
fun Mangatest() {
    Text(text = "MangaList")
}

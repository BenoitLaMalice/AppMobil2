package com.example.appmobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.appmobile.ui.viewmodels.MangaViewModel

@Composable
fun Home(navController: NavController,   modifier: Modifier = Modifier){
    //viewModel: MangaViewModel= hiltViewModel(),
    //val mangaRandom =viewModel.uiState.collectAsState()
    Column (modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Hometest()
    }
}

@Composable
fun Hometest() {
    Text(text = "Home")
}

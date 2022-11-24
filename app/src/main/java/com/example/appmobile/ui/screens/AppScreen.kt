package com.example.appmobile.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.appmobile.R
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon : ImageVector){
    object Discover : Screen("discover", R.string.discover, Icons.Filled.Search)
    object MangaList : Screen("manga_list", R.string.manga_list, Icons.Filled.List)
    object Profile : Screen("profile", R.string.profile, Icons.Filled.Person)
    object Home : Screen("home",R.string.home,Icons.Filled.Home)
    object Settings : Screen("setting",R.string.setting,Icons.Filled.Settings)
}

@Composable
fun TopBar(@StringRes title: Int,
           canNavigateBack: Boolean,
           modifier: Modifier = Modifier,
           navigateBack: ()->Unit){
    TopAppBar(
        title = { Text(stringResource(id = title)) },
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun BottomBar(navController: NavController, modifier: Modifier = Modifier, onNavigate: (Screen)->Unit) {
    val items = listOf(
        Screen.Discover,
        Screen.MangaList,
        Screen.Profile,
        Screen.Home,
        Screen.Settings
    )
    BottomNavigation(modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach{screen ->
            BottomNavigationItem(
            icon = { Icon(screen.icon, contentDescription = null)},
            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                onNavigate(screen)
            }
            )
        }
    }
}

@Composable
fun AppScreen(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentScreenTitle by remember {
        mutableStateOf(Screen.Profile.resourceId)
    }

    var canNavigateBack by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopBar(title = currentScreenTitle, canNavigateBack = canNavigateBack, modifier) {
                navController.navigateUp()
            }
        },
        bottomBar = {
            BottomBar(navController, modifier) {
                currentScreenTitle = it.resourceId
            }
        }) {
            innerPadding ->
        NavHost(navController, startDestination = Screen.Profile.route, Modifier.padding(innerPadding)) {
            composable(Screen.Profile.route){ Profile(navController = navController, Modifier.padding(innerPadding))}
            composable(Screen.MangaList.route) { MangaList(navController = navController, Modifier.padding(innerPadding))}
            composable(Screen.Discover.route){ Discover(navController = navController, Modifier.padding(innerPadding))}
            composable(Screen.Home.route){ Home(navController = navController,Modifier.padding(innerPadding))}
            composable(Screen.Settings.route){ Setting(navController = navController, Modifier.padding(innerPadding))}
        }
    }
}



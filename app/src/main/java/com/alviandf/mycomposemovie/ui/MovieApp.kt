package com.alviandf.mycomposemovie.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alviandf.mycomposemovie.navigation.Screen
import com.alviandf.mycomposemovie.ui.widgets.BottomBar
import com.alviandf.mycomposemovie.ui.widgets.TopBar

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != Screen.Detail.route) {
                TopBar(
                    isBackVisible = currentRoute == Screen.About.route,
                    isAboutVisible = currentRoute != Screen.About.route,
                    onBackClick = { navController.navigateUp() },
                    onAboutClick = { navController.navigate(Screen.About.route) }
                )
            }
        },
        bottomBar = {
            if (currentRoute != Screen.Detail.route && currentRoute != Screen.About.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Popular.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Popular.route) {
                PopularMovieScreen(onNavigateDetail = {
                    navController.navigate(Screen.Detail.createRoute(it))
                })
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(onNavigateDetail = {
                    navController.navigate(Screen.Detail.createRoute(it))
                })
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("movieId") ?: -1
                DetailScreen(
                    movieId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
        }
    }
}
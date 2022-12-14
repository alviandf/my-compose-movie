package com.alviandf.mycomposemovie.navigation

sealed class Screen(val route: String) {
    object Popular : Screen("popular")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object Detail : Screen("detail/{movieId}") {

        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}

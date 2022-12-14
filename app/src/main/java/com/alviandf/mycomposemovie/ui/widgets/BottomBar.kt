package com.alviandf.mycomposemovie.ui.widgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alviandf.mycomposemovie.R
import com.alviandf.mycomposemovie.navigation.NavigationItem
import com.alviandf.mycomposemovie.navigation.Screen.Favorite
import com.alviandf.mycomposemovie.navigation.Screen.Popular
import com.alviandf.mycomposemovie.ui.theme.Black100

@Composable
fun BottomBar(
    navController: NavController
) {
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = Black100,
        contentColor = Black100,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.title_popular),
                icon = Icons.Default.PlayArrow,
                screen = Popular
            ),
            NavigationItem(
                title = stringResource(id = R.string.title_favorite),
                icon = Icons.Default.Favorite,
                screen = Favorite
            ),
        )
        BottomNavigation {
            navigationItems.map {
                BottomNavigationItem(
                    selected = currentRoute == it.screen.route,
                    onClick = {
                        navController.navigate(it.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title
                        )
                    },
                    label = {
                        Text(text = it.title)
                    }
                )
            }
        }
    }
}
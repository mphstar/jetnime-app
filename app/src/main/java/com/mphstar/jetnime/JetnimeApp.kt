package com.mphstar.jetnime

import DetailAnimeScreen
import FavoriteScreen
import ProfileScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mphstar.jetnime.navigation.NavigationBarItem
import com.mphstar.jetnime.navigation.Screen
import com.mphstar.jetnime.screen.favorite.FavoriteViewModel
import com.mphstar.jetnime.screen.home.HomeScreen
import com.mphstar.jetnime.screen.home.HomeViewModel

private val viewModel = HomeViewModel()
private val viewModelFavorite = FavoriteViewModel()

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationBarItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )

        navigationItems.map { item ->

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color(0xFF8A8A8A),
                    unselectedTextColor = Color(0xFF8A8A8A)
                ),
                selected = currentRoute == item.screen.route,
                label = {
                    Text(text = item.title)
                },
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },

                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetNime(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route



    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailAnime.route) {
                BottomNavigationBar(navController = navController)
            }
        },
        modifier = modifier,

        ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    navigateToDetail = { animeId ->
                        navController.navigate(Screen.DetailAnime.createRoute(animeId))
                    }
                )
            }

            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    viewModel = viewModelFavorite,
                    navigationToDetail = { animeId ->
                        navController.navigate(Screen.DetailAnime.createRoute(animeId))
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = Screen.DetailAnime.route,
                arguments = listOf(navArgument("animeId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("animeId") ?: 0

                DetailAnimeScreen(
                    animeId = id,
                    viewModel = viewModel,
                    navigateBack = {

                    },
                    viewModelFavorite = viewModelFavorite,
                    navigateToDetail = { animeId ->
                        navController.navigate(Screen.DetailAnime.createRoute(animeId))
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun JetNimePreview() {
    JetNime()
}

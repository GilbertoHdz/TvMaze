package com.gilbertohdz.tvmaze.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gilbertohdz.tvmaze.compose.navigation.NavigationEvent
import com.gilbertohdz.tvmaze.compose.navigation.Route
import com.gilbertohdz.tvmaze.compose.ui.screens.catalog.CatalogBrowser
import com.gilbertohdz.tvmaze.compose.ui.screens.details.DetailsScreen
import com.gilbertohdz.tvmaze.compose.ui.screens.search.SearchScreen

@Composable
fun App(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Route.HOME.route) {
        composable(Route.HOME.route) {
            CatalogBrowser(
                onNavigation = { nevEvent ->
                    if (nevEvent is NavigationEvent.NavigateDetail) {
                        navController.navigate("/${Route.DETAIL.route}/${nevEvent.movie.id}")
                    }
                    if (nevEvent is NavigationEvent.NavigateSearch) {
                        navController.navigate(Route.SEARCH.route)
                    }
                },
            )
        }
        composable(
            route = "/${Route.DETAIL.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailsScreen(
                backAction = {
                    navController.popBackStack()
                    navController.navigate(Route.HOME.route)
                }
            )
        }
        composable(
            route = Route.SEARCH.route
        ) {
            SearchScreen(
                onNavigation = { navEvent ->
                    if (navEvent is NavigationEvent.NavigateDetail) {
                        navController.navigate("/${Route.DETAIL.route}/${navEvent.movie.id}")
                    }
                },
            )
        }
    }
}
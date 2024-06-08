package com.gilbertohdz.tvmaze.compose.navigation

import com.gilbertohdz.tvmaze.compose.data.Movie

sealed class NavigationEvent {
    data class NavigateDetail(val route: Route, val movie: Movie) : NavigationEvent()
    data class NavigateSearch(val route: Route) : NavigationEvent()
}
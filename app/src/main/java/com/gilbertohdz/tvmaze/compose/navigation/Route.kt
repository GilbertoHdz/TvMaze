package com.gilbertohdz.tvmaze.compose.navigation

enum class Route(val route: String, val visibleOnTopBar: Boolean) {
    HOME("/", true),
    DETAIL("detail", false),
    PLAYER("player", false),
    SHOWS("shows", true),
    FAVORITES("favorites", true),
    SEARCH("search", true)
}

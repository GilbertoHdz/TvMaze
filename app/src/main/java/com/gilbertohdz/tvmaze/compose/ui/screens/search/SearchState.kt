package com.gilbertohdz.tvmaze.compose.ui.screens.search

import com.gilbertohdz.tvmaze.compose.data.Movie

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val movies: List<Movie> = emptyList()
)
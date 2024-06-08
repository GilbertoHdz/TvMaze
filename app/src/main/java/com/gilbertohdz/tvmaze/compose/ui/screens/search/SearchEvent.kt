package com.gilbertohdz.tvmaze.compose.ui.screens.search

import com.gilbertohdz.tvmaze.compose.data.Movie

sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()
    data object OnSearch : SearchEvent()
    data class OnMovieClick(
        val movie: Movie
    ): SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent()
}
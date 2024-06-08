package com.gilbertohdz.tvmaze.compose.ui.screens.catalog

import com.gilbertohdz.tvmaze.compose.data.Movie

sealed class CatalogEvent {
    data class OnMovieClick(val movie: Movie) : CatalogEvent()
    data object OnNavSearchClick : CatalogEvent()
}
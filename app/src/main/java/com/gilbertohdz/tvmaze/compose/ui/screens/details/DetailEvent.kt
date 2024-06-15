package com.gilbertohdz.tvmaze.compose.ui.screens.details

import com.gilbertohdz.tvmaze.compose.data.Movie

sealed class DetailEvent {
    data class OnNavPlayerScreen(val movie: Movie) : DetailEvent()
}
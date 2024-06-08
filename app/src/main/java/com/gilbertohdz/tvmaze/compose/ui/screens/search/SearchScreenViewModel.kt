package com.gilbertohdz.tvmaze.compose.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilbertohdz.domain.use_case.TvMazeUseCases
import com.gilbertohdz.tvmaze.compose.data.Movie
import com.gilbertohdz.tvmaze.compose.data.toMovie
import com.gilbertohdz.tvmaze.compose.navigation.NavigationEvent
import com.gilbertohdz.tvmaze.compose.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tvMazeUseCases: TvMazeUseCases
) : ViewModel() {

    var state by mutableStateOf(SearchState())
    private val _navEvent = Channel<NavigationEvent>()
    val navEvent = _navEvent.receiveAsFlow()
    
    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is SearchEvent.OnSearch -> {
                executeSearch()
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
            is SearchEvent.OnMovieClick -> {
                navToDetailScreen(event.movie)
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                movies = emptyList()
            )

            tvMazeUseCases
                .getShowsBySearch(state.query)
                .onSuccess { shows ->
                    state = state.copy(
                        movies = shows.map {
                            it.toMovie()
                        },
                        isSearching = false,
                        query = ""
                    )
                }
                .onFailure { e ->
                    e.printStackTrace()
                    state = state.copy(isSearching = false)
                }
        }
    }

    private fun navToDetailScreen(movie: Movie) {
        viewModelScope.launch {
            _navEvent.send(NavigationEvent.NavigateDetail(Route.DETAIL, movie))
        }
    }
}

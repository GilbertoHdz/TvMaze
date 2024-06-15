package com.gilbertohdz.tvmaze.compose.ui.screens.details

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tvMazeUseCases: TvMazeUseCases
) : ViewModel() {

    private val _navEvent = Channel<NavigationEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    private val movieFlow = savedStateHandle.getStateFlow<Int?>("id", null)

    val detailState: StateFlow<DetailsLoadingState> = flow {
        tvMazeUseCases.getShowById(movieFlow.value ?: error("Show Id should not be null"))
            .onSuccess {
                emit(DetailsLoadingState.Ready(movie = it.toMovie()))
            }
            .onFailure {
                emit(DetailsLoadingState.NotFound)
            }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        DetailsLoadingState.Loading
    )

    val episodes: StateFlow<EpisodesState> = flow {
        tvMazeUseCases.getEpisodesById(movieFlow.value ?: error("Show Id should not be null"))
            .onSuccess { values ->

                val episodes = values
                    .map { it.toMovie() }
                    .groupBy { it.season }

                if (episodes.isEmpty()) {
                    emit(EpisodesState.NotFound)
                } else {
                    emit(EpisodesState.Ready(season = episodes.keys.map { it }, episodes = episodes ))
                }
            }
            .onFailure {
                emit(EpisodesState.NotFound)
            }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        EpisodesState.Loading
    )

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnNavPlayerScreen -> { navToPlayerScreen(event.movie.id) }
        }
    }

    private fun navToPlayerScreen(movieId: Int) {
        viewModelScope.launch {
            _navEvent.send(NavigationEvent.NavigatePlayer(Route.PLAYER, movieId = movieId))
        }
    }
}

sealed interface DetailsLoadingState {
    data object Loading : DetailsLoadingState
    data object NotFound : DetailsLoadingState
    class Ready(val movie: Movie) : DetailsLoadingState
}

sealed interface EpisodesState {
    data object Loading : EpisodesState
    data object NotFound : EpisodesState
    class Ready(val season: List<Int>, val episodes: Map<Int, List<Movie>>) : EpisodesState
}
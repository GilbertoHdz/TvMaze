package com.gilbertohdz.tvmaze.compose.ui.screens.catalog

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilbertohdz.domain.use_case.TvMazeUseCases
import com.gilbertohdz.tvmaze.compose.data.Movie
import com.gilbertohdz.tvmaze.compose.data.toMovie
import com.gilbertohdz.tvmaze.compose.navigation.NavigationEvent
import com.gilbertohdz.tvmaze.compose.navigation.Route
import com.gilbertohdz.tvmaze.compose.ui.screens.search.SearchEvent
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
class CatalogBrowserViewModel @Inject constructor(
    private val tvMazeUseCases: TvMazeUseCases
) : ViewModel() {

    private val _navEvent = Channel<NavigationEvent>()
    val navEvent = _navEvent.receiveAsFlow()


    val featuredMovieList: StateFlow<List<Movie>> = flow {
        tvMazeUseCases.getShowsByPage()
            .onSuccess { shows ->
                val movies = shows.map { it.toMovie() }
                emit(movies)
            }
            .onFailure {
                emit(emptyList<Movie>())
            }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())


    val categoryList: StateFlow<List<Category>> = flow {
        var list = listOf<Category>()
        listOf("Anime", "Comedy", "Drama", "Horror", "Science-Fiction").forEach { category ->
            tvMazeUseCases.getShowsBySearch(query = category)
                .onSuccess { shows ->
                    val categoryData = Category(
                        name = category,
                        movieList =  shows.map { it.toMovie() }
                    )
                    list = list + listOf(categoryData)
                    emit(list)
                }
                .onFailure {
                    emit(list)
                }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    fun onEvent(event: CatalogEvent) {
        when (event) {
            is CatalogEvent.OnMovieClick -> {
                navToDetailScreen(event.movie)
            }
            is CatalogEvent.OnNavSearchClick -> {
                navToSearchScreen()
            }
        }
    }

    private fun navToDetailScreen(movie: Movie) {
        viewModelScope.launch {
            _navEvent.send(NavigationEvent.NavigateDetail(Route.DETAIL, movie))
        }
    }

    private fun navToSearchScreen() {
        viewModelScope.launch {
            _navEvent.send(NavigationEvent.NavigateSearch(Route.SEARCH))
        }
    }
}

@Stable
data class Category(val name: String, val movieList: List<Movie>)
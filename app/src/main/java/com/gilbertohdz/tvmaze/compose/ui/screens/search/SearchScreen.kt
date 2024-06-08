package com.gilbertohdz.tvmaze.compose.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import com.gilbertohdz.tvmaze.compose.navigation.NavigationEvent
import com.gilbertohdz.tvmaze.compose.ui.components.SearchTextField
import com.gilbertohdz.tvmaze.compose.ui.components.VerticalCompactCard

@Composable
fun SearchScreen(
    onNavigation: (NavigationEvent) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = keyboardController) {
        viewModel.navEvent.collect { event ->
            onNavigation(event)
        }
    }

    Column(
        modifier = modifier
            .padding(PaddingValues(horizontal = 58.dp, vertical = 38.dp)),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SearchTextField(
            text = state.query,
            onValueChange = {
                viewModel.onEvent(SearchEvent.OnQueryChange(it))
            },
            shouldShowHint = state.isHintVisible,
            onSearch = {
                keyboardController?.hide()
                viewModel.onEvent(SearchEvent.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
            }
        )

        TvLazyVerticalGrid(
            columns = TvGridCells.Fixed(6),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(state.movies) {
                VerticalCompactCard(movie = it) { movie ->
                    viewModel.onEvent(SearchEvent.OnMovieClick(movie))
                }
            }
        }
    }
}
package com.gilbertohdz.tvmaze.compose.ui.screens.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gilbertohdz.tvmaze.compose.navigation.NavigationEvent
import com.gilbertohdz.tvmaze.compose.ui.components.LoadingComponent
import com.gilbertohdz.tvmaze.compose.ui.components.NotFoundAndBackComponent

@Composable
fun DetailsScreen(
    backAction: () -> Unit,
    onNavigation: (NavigationEvent) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val state by viewModel.detailState.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    )

    when (val s = state) {
        is DetailsLoadingState.Ready -> Details(
            movie = s.movie,
            modifier = modifier,
            viewModel = viewModel,
            onNavigation = onNavigation
        )

        is DetailsLoadingState.NotFound -> NotFoundAndBackComponent(
            backAction = backAction,
            modifier = modifier
                .fillMaxSize()
        )

        else -> LoadingComponent(modifier = modifier.fillMaxSize())
    }
}


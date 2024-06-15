package com.gilbertohdz.tvmaze.compose.ui.screens.details

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.gilbertohdz.tvmaze.R
import com.gilbertohdz.tvmaze.compose.data.Movie
import com.gilbertohdz.tvmaze.compose.navigation.NavigationEvent
import com.gilbertohdz.tvmaze.compose.ui.components.AnimateAsyncCoverImage
import com.gilbertohdz.tvmaze.compose.ui.components.HorizontalCompactCard
import com.gilbertohdz.tvmaze.compose.ui.components.LoadingComponent
import com.gilbertohdz.tvmaze.compose.ui.components.NotFoundComponent
import com.gilbertohdz.tvmaze.compose.ui.components.SeasonTopBar


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Details(
    movie: Movie,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel,
    onNavigation: (NavigationEvent) -> Unit = {},
    ) {
    val episodes by viewModel.episodes.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    )
    var selectedSeason by remember { mutableStateOf(-1) }
    var currentMovie by remember { mutableStateOf(movie) }

    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.navEvent.collect { event ->
            onNavigation(event)
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AnimateAsyncCoverImage(currentMovie)
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.background,
                            Color.Transparent
                        )
                    )
                )
                .fillMaxSize()
        ) {

            TvLazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(vertical = 38.dp)
            ) {

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 58.dp)
                    ) {
                        Text(
                            text = currentMovie.title,
                            style = MaterialTheme.typography.displayMedium
                        )
                        Text(
                            text = currentMovie.studio,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = HtmlCompat.fromHtml(currentMovie.description, 0).toString()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.onEvent(DetailEvent.OnNavPlayerScreen(currentMovie)) }) {
                            Text(
                                text = "Watch Now"
                            )
                        }
                    }
                }

                when (val e = episodes) {
                    is EpisodesState.Ready -> {
                        if (e.season.isNotEmpty()) {
                            item {
                                SeasonTopBar(e.season) {
                                    selectedSeason = it
                                }
                            }
                        }

                        if (e.episodes.isNotEmpty()) {
                            selectedSeason.let {
                                val currentIndex = if (it == -1) {
                                    e.season.first()
                                } else it

                                e.episodes[currentIndex]?.let { movies ->
                                    item {
                                        Crossfade(
                                            movies,
                                            label = "AnimatedContent-DetailScreen-Episodes"
                                        ) { elements ->
                                            Episodes(elements) { movie ->
                                                currentMovie = movie
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    is EpisodesState.NotFound -> item {
                        NotFoundComponent(
                            text = stringResource(R.string.detail_no_episodes),
                            modifier = modifier.fillMaxSize())
                    }
                    else -> item {
                        LoadingComponent(modifier = modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
private fun Episodes(episodes: List<Movie>, onClick: (Movie) -> Unit) {
    TvLazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 58.dp),
        modifier = Modifier
            .height(100.dp)
    ) {

        items(episodes) { episode ->
            HorizontalCompactCard(
                episode,
                onClick = {
                    onClick(it)
                }
            )
        }
    }
}

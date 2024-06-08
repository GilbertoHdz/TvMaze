package com.gilbertohdz.tvmaze.compose.ui.screens.catalog

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Button
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.gilbertohdz.tvmaze.R
import com.gilbertohdz.tvmaze.compose.navigation.NavigationEvent
import com.gilbertohdz.tvmaze.compose.navigation.Route
import com.gilbertohdz.tvmaze.compose.ui.components.AnimateAsyncCoverImage
import com.gilbertohdz.tvmaze.compose.ui.components.NavigationTopBar
import com.gilbertohdz.tvmaze.compose.ui.components.VerticalCompactCard

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CatalogBrowser(
    modifier: Modifier = Modifier,
    viewModel: CatalogBrowserViewModel = hiltViewModel(),
    onNavigation: (NavigationEvent) -> Unit = {},
) {

    val featuredMovieList by viewModel.featuredMovieList.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    )

    val categoryList by viewModel.categoryList.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    )

    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.navEvent.collect { event ->
            onNavigation(event)
        }
    }

    TvLazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(vertical = 36.dp)
    ) {

        item {
            NavigationTopBar { route ->
                if (route == Route.SEARCH) {
                    viewModel.onEvent(CatalogEvent.OnNavSearchClick)
                }
            }
        }

        item {

            Carousel(
                itemCount = featuredMovieList.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 58.dp)
                    .height(376.dp),
            ) { indexOfCarouselItem ->
                val featuredMovie = featuredMovieList[indexOfCarouselItem]
                val backgroundColor = MaterialTheme.colorScheme.background

                Box {
                    AnimateAsyncCoverImage(featuredMovie)
                    Box(
                        contentAlignment = Alignment.BottomStart,
                        modifier = Modifier
                            .fillMaxSize()
                            .drawBehind {
                                val brush = Brush.horizontalGradient(
                                    listOf(backgroundColor, Color.Transparent)
                                )
                                drawRect(brush)
                            }
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Text(
                                text = featuredMovie.title,
                                style = MaterialTheme.typography.displaySmall
                            )
                            Spacer(modifier = Modifier.height(28.dp))
                            Button(onClick = {
                                viewModel.onEvent(CatalogEvent.OnMovieClick(featuredMovie))
                            }) {
                                Text(text = stringResource(id = R.string.show_details))
                            }
                            Spacer(modifier = Modifier.height(28.dp))
                        }
                    }
                }
            }
        }

        items(categoryList) { category ->
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(start = 58.dp, bottom = 24.dp),
                )

            TvLazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 58.dp),
                modifier = Modifier
                    .height(200.dp)
            ) {
                items(category.movieList) { movie ->
                    VerticalCompactCard(
                        movie,
                        onClick = {
                            viewModel.onEvent(CatalogEvent.OnMovieClick(it))
                        }
                    )
                }
            }
        }
    }
}


package com.gilbertohdz.tvmaze.compose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.gilbertohdz.tvmaze.R
import com.gilbertohdz.tvmaze.compose.data.Movie

/**
 * Vertical card banner
 * - Title
 * - Date
 * - Image [FillBounds] (for Vertical purpose)
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun VerticalCompactCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: (Movie) -> Unit = {}
) {
    CompactCard(
        onClick = { onClick(movie) },
        modifier = modifier
            .aspectRatio(CardDefaults.VerticalImageAspectRatio),
        title = {
            Text(
                text = movie.title,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        },
        subtitle = {
            Text(
                text = "2020 - Cats - 2h 8m",
                modifier = Modifier.padding(8.dp)
            )
        },
        // glow = CardDefaults.glow(glow = Glow(elevationColor = Color.Cyan, elevation = 8.dp)),
        image = {
            AsyncImage(
                model = movie.cardImageUrl,
                contentDescription = movie.description,
                contentScale = ContentScale.FillBounds,
            )
        },
    )
}


/**
 * Horizontal card banner
 * - Title (2 lines Max)
 * - Season #
 * - Date
 * - Image [FillWidth] (for horizontal purpose)
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HorizontalCompactCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: (Movie) -> Unit = {}
) {
    CompactCard(
        onClick = { onClick(movie) },
        modifier = modifier
            .aspectRatio(CardDefaults.HorizontalImageAspectRatio),
        title = {
            Text(
                text = movie.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        },
        subtitle = {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.season_param, movie.season),
                    modifier = Modifier.padding(start = 8.dp)
                )
                movie.premiered?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                } ?: Spacer(modifier = Modifier.height(8.dp))
            }
        },
        image = {
            AsyncImage(
                model = movie.cardImageUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.tvm_header_logo)
            )
        },
    )
}
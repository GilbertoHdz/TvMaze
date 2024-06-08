package com.gilbertohdz.tvmaze.compose.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.gilbertohdz.tvmaze.compose.data.Movie

@Composable
fun AnimateAsyncCoverImage(currentMovie: Movie) {
    AnimatedContent(
        currentMovie,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 1500)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 500))
        },
        label = "AnimatedContent-DetailScreen"
    ) { targetState ->
        AsyncImage(
            model = targetState.backgroundImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
package com.gilbertohdz.tvmaze.compose.ui.screens.player

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.gilbertohdz.player.ui.viewmodels.PlayerViewModel
import com.gilbertohdz.player.utils.logs.LogCompositions
import com.gilbertohdz.tvmaze.compose.ui.components.NextButton
import com.gilbertohdz.tvmaze.compose.ui.components.PlayPauseButton
import com.gilbertohdz.tvmaze.compose.ui.components.PreviousButton
import com.gilbertohdz.tvmaze.compose.ui.components.RewindButton
import com.gilbertohdz.tvmaze.compose.ui.components.Seekbar
import com.gilbertohdz.tvmaze.compose.ui.components.SkipButton
import com.gilbertohdz.tvmaze.compose.ui.theme.AppDefaults
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun TvPlayerControls(
    modifier: Modifier = Modifier,
    viewModel: PlayerViewModel = hiltViewModel(),
) {
    LogCompositions(tag = "TvPlayerControls")

    // Requesters
    val controlsRowRequest = remember { FocusRequester() }
    val playPauseButton = remember { FocusRequester() }

    // Behaviors
    val controlsVisibility = remember(viewModel.showControls) { viewModel.showControls }
    val isPlaying = remember(viewModel.isPlaying) { viewModel.isPlaying }
    val duration = remember(viewModel.duration) { viewModel.duration }
    val currentPosition = remember(viewModel.currentPosition) { viewModel.currentPosition }
    val buffer = remember(viewModel.bufferedPercentage) { viewModel.bufferedPercentage }
    val playerState = remember(viewModel.playerState) { viewModel.playerState }

    LaunchedEffect(controlsVisibility) {
        if (controlsVisibility) {
            delay(100)
            controlsRowRequest.requestFocus()
        }
    }

    BackHandler(enabled = controlsVisibility) {
        viewModel.showControls = !controlsVisibility
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = controlsVisibility,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppDefaults.gap.item),
            modifier = modifier,
        ) {

            ElapsedTimeIndicator(currentPosition, duration, {  }, {  })

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    AppDefaults.gap.default,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(controlsRowRequest)
                    .onFocusChanged {
                        if (it.isFocused) {
                            playPauseButton.requestFocus()
                        }
                    }
                    .focusable(),
            ) {
                PreviousButton(
                    onClick = {  },
                    modifier = Modifier.size(AppDefaults.iconButtonSize.medium.intoDpSize())
                )

                RewindButton(
                    onClick = { },
                    modifier = Modifier.size(AppDefaults.iconButtonSize.medium.intoDpSize())
                )

                PlayPauseButton(
                    isPlaying = isPlaying,
                    onClick = {
                        if (isPlaying) {
                            // pause()
                        } else {
                            // play()
                        }
                    },
                    modifier = Modifier
                        .size(AppDefaults.iconButtonSize.large.intoDpSize())
                        .focusRequester(playPauseButton)
                )

                SkipButton(
                    onClick = {  },
                    modifier = Modifier.size(AppDefaults.iconButtonSize.medium.intoDpSize())
                )

                NextButton(
                    onClick = { },
                    modifier = Modifier.size(AppDefaults.iconButtonSize.medium.intoDpSize())
                )
            }
        }
    }
}

@Composable
private fun ElapsedTimeIndicator(
    currentPosition: Int,
    duration: Int,
    skip: () -> Unit,
    rewind: () -> Unit,
    modifier: Modifier = Modifier,
    knobSize: Dp = 8.dp
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppDefaults.gap.tiny)
    ) {
        // ElapsedTime(timeElapsed = timeElapsed, length = length)
        Seekbar(
            currentPosition = currentPosition,
            duration = duration,
            knobSize = knobSize,
            onMoveLeft = rewind,
            onMoveRight = skip,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlayerControls() {
    TvPlayerControls(
        modifier = Modifier.fillMaxSize(),
    )
}
package com.gilbertohdz.player.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.gilbertohdz.player.api.PlayerState
import com.gilbertohdz.player.ui.viewmodels.PlayerViewModel
import com.gilbertohdz.player.utils.logs.LogCompositions
import com.gilbertohdz.player.utils.stringForTime

@OptIn(ExperimentalAnimationApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun PlayerControls(
    modifier: Modifier = Modifier,
    viewModel: PlayerViewModel = hiltViewModel(),
    getTitle: () -> String,
    isFullScreen: Boolean,
    onPauseToggle: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onReplay: () -> Unit,
    onForward: () -> Unit,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit
) {
    LogCompositions(tag = "PlayerControls")

    // UI
    val title = remember(getTitle()) { getTitle() }

    // Behaviors
    val controlsVisibility = remember(viewModel.showControls) { viewModel.showControls }
    val isPlaying = remember(viewModel.isPlaying) { viewModel.isPlaying }
    val duration = remember(viewModel.duration) { viewModel.duration }
    val currentPosition = remember(viewModel.currentPosition) { viewModel.currentPosition }
    val buffer = remember(viewModel.bufferedPercentage) { viewModel.bufferedPercentage }
    val playerState = remember(viewModel.playerState) { viewModel.playerState }

    AnimatedVisibility(
        modifier = modifier,
        visible = controlsVisibility,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .testTag("PlayerControlsParent")
               // .background(EpicWorldTheme.colors.background.copy(alpha = 0.6f))
        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .testTag("VideoTitle")
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight: Int -> -fullHeight }
                        ),
                        exit = shrinkVertically()
                    ),
                text = title,
               // style = EpicWorldTheme.typography.subTitle2,
               // color = EpicWorldTheme.colors.onBackground
            )

            val controlButtonModifier: Modifier = remember(isFullScreen) {
                if (isFullScreen) {
                    Modifier
                        .padding(horizontal = 8.dp)
                        .size(40.dp)
                } else {
                    Modifier.size(32.dp)
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .testTag("VideoControlParent"),
                horizontalArrangement = if (isFullScreen) {
                    Arrangement.Center
                } else {
                    Arrangement.SpaceEvenly
                }
            ) {
                IconButton(
                    modifier = controlButtonModifier,
                    onClick = onPrevious
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = androidx.media3.ui.R.drawable.exo_ic_skip_previous),
                        contentDescription = "play_previous"
                    )
                }

                IconButton(
                    modifier = controlButtonModifier,
                    onClick = onReplay
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = androidx.media3.ui.R.drawable.exo_icon_repeat_all),
                        contentDescription = "rewind_5"
                    )
                }

                IconButton(
                    modifier = controlButtonModifier,
                    onClick = onPauseToggle
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(
                            id =
                            when {
                                isPlaying -> {
                                    androidx.media3.ui.R.drawable.exo_ic_pause_circle_filled
                                }
                                isPlaying.not() && playerState == PlayerState.ENDED -> {
                                    androidx.media3.ui.R.drawable.exo_icon_repeat_all
                                }
                                else -> {
                                    androidx.media3.ui.R.drawable.exo_ic_play_circle_filled
                                }
                            }
                        ),
                        contentDescription = "toggle_play"
                    )
                }

                IconButton(
                    modifier = controlButtonModifier,
                    onClick = onForward
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = androidx.media3.ui.R.drawable.exo_icon_rewind),
                        contentDescription = "forward_10"
                    )
                }

                IconButton(
                    modifier = controlButtonModifier,
                    onClick = onNext
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = androidx.media3.ui.R.drawable.exo_ic_skip_next),//R.drawable.ic_skip_next
                        contentDescription = "play_next"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = if (isFullScreen) 32.dp else 16.dp)
                    .testTag("VideoSeek")
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight: Int -> fullHeight }
                        ),
                        exit = slideOutVertically(
                            targetOffsetY = { fullHeight: Int -> fullHeight }
                        )
                    )
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Slider(
                        value = buffer.toFloat(),
                        enabled = false,
                        onValueChange = { /*do nothing*/ },
                        valueRange = 0f..100f,
                        colors = SliderDefaults.colors(
                            disabledThumbColor = Color.Transparent
                        )
                    )

                    Slider(
                        value = currentPosition.toFloat(),
                        onValueChange = {
                            // onSeekChanged.invoke(it)
                        },
                        valueRange = 0f..duration.toFloat(),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .testTag("VideoCurrentPosition")
                            .padding(start = 16.dp)
                            .animateEnterExit(
                                enter = slideInVertically(
                                    initialOffsetY = { fullHeight: Int -> fullHeight }
                                ),
                                exit = slideOutVertically(
                                    targetOffsetY = { fullHeight: Int -> fullHeight }
                                )
                            ),
                        text = stringForTime(currentPosition),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Text(
                        modifier = Modifier
                            .testTag("VideoDuration")
                            .padding(start = 16.dp)
                            .animateEnterExit(
                                enter = slideInVertically(
                                    initialOffsetY = { fullHeight: Int -> fullHeight }
                                ),
                                exit = slideOutVertically(
                                    targetOffsetY = { fullHeight: Int -> fullHeight }
                                )
                            ),
                        text = stringForTime(duration.toInt()),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )

                    IconButton(
                        modifier = Modifier
                            .testTag("FullScreenToggleButton")
                            .padding(end = 16.dp)
                            .size(24.dp)
                            .animateEnterExit(
                                enter = slideInVertically(
                                    initialOffsetY = { fullHeight: Int -> fullHeight }
                                ),
                                exit = slideOutVertically(
                                    targetOffsetY = { fullHeight: Int -> fullHeight }
                                )
                            ),
                        onClick = {
                            if (isFullScreen.not()) {
                               // context.setLandscape()
                            } else {
                               // context.setPortrait()
                            }.also {
                                onFullScreenToggle.invoke(isFullScreen.not())
                            }
                        }
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(
                                id = if (isFullScreen) {
                                    androidx.media3.ui.R.drawable.exo_icon_fullscreen_exit
                                } else {
                                    androidx.media3.ui.R.drawable.exo_icon_fullscreen_enter
                                }
                            ),
                            contentDescription = "toggle_full_screen"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlayerControls() {
    PlayerControls(
        modifier = Modifier.fillMaxSize(),
        isFullScreen = false,
        onForward = {},
        onNext = {},
        onPauseToggle = {},
        onPrevious = {},
        onReplay = {},
        onFullScreenToggle = {},
        getTitle = { "" },
    )
}
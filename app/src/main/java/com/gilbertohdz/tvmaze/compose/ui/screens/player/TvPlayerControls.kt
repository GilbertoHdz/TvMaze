package com.gilbertohdz.tvmaze.compose.ui.screens.player

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
import com.gilbertohdz.tvmaze.compose.ui.components.Seekbar
import com.gilbertohdz.tvmaze.compose.ui.theme.AppDefaults

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
                ElapsedTimeIndicator(currentPosition, duration, {  }, {  })
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
    PlayerControls(
        modifier = Modifier.fillMaxSize(),
        isFullScreen = false,
        onForward = {},
        onNext = {},
        onPauseToggle = {},
        onPrevious = {},
        onReplay = {},
        getTitle = { "" },
    )
}
package com.gilbertohdz.tvmaze.compose.ui.screens.player

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.gilbertohdz.player.api.PlayerControllerFactory
import com.gilbertohdz.player.api.configuration.PlayerConfig
import com.gilbertohdz.player.ui.views.VideoPlayer
import com.gilbertohdz.tvmaze.compose.ui.components.Seekbar
import com.gilbertohdz.tvmaze.compose.ui.theme.AppDefaults
import java.time.Duration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlayerScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
            }
            .background(color = Color.DarkGray),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        val playerController = PlayerControllerFactory.createPlayerController()
        val playerConfig = PlayerConfig()
        playerController.setup(playerConfig)

        VideoPlayer(
            playerController = playerController,
            modifier = Modifier.fillMaxSize()
        ) {
            PlayerControls(
                modifier = Modifier.fillMaxSize(),
                getTitle = { "Gilinho" },
                isFullScreen = true,
                onPrevious = {
                    // playerWrapper.exoPlayer.seekToPrevious()
                },
                onNext = {
                    // playerWrapper.exoPlayer.seekToNext()
                },
                onReplay = {
                    // playerWrapper.exoPlayer.seekBack()
                },
                onForward = {
                    // playerWrapper.exoPlayer.seekForward()
                },
                onPauseToggle = {
                    if (playerController.isPlaying) {
                        playerController.pause()
                    } else {
                        playerController.play()
                    }
                },
            )
        }
    }
}
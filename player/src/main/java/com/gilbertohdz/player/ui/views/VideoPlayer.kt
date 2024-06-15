package com.gilbertohdz.player.ui.views

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.gilbertohdz.player.api.PlayerController
import com.gilbertohdz.player.api.internal.controllers.PlayerControllerImpl
import com.gilbertohdz.player.ui.viewmodels.PlayerViewModel
import com.gilbertohdz.player.utils.logs.LogCompositions


@ExperimentalAnimationApi
@Composable
fun VideoPlayer(
    playerController: PlayerController,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    LogCompositions(tag = "VideoPlayer")

    val context = LocalContext.current
    val viewModel: PlayerViewModel = hiltViewModel()
    val exoPlayer = remember {
        viewModel.buildExoPlayer(context, playerController as PlayerControllerImpl)
    }

    androidx.compose.ui.platform.LocalLifecycleOwner.current.lifecycle.addObserver(viewModel.handleLifecycle(playerController))

    Player(exoPlayer = exoPlayer, context = context, modifier = modifier) {
        viewModel.showControls = !viewModel.showControls
    }

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
                        if (viewModel.isPlaying) {
                            playerController.pause()
                        } else {
                            playerController.play()
                        }
            /*when {
                playerWrapper.exoPlayer.isPlaying -> {
                    playerWrapper.exoPlayer.pause()
                }
                playerWrapper.exoPlayer.isPlaying.not() && playbackState == STATE_ENDED -> {
                    playerWrapper.exoPlayer.seekTo(0, 0)
                    playerWrapper.exoPlayer.playWhenReady = true
                }
                else -> {
                    playerWrapper.exoPlayer.play()
                }
            }
            isPlaying = isPlaying.not()
            */
        },
    ) {

    }
}

@OptIn(UnstableApi::class)
@ExperimentalAnimationApi
@Composable
internal fun Player(
    exoPlayer: ExoPlayer,
    context: Context,
    modifier: Modifier,
    onTouchScreen: () -> Unit
) {

    val playerView = remember {
        PlayerView(context).apply {
            player = exoPlayer
            // Disable player controls
            useController = false
            // Set resize mode to fill the available space
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    Box(
        modifier = modifier
            .background(Color.Black)
            .testTag("VideoPlayer-Player")
            .clickable { onTouchScreen() }
    ) {
        // DisposableEffect to release the ExoPlayer when the Composable is disposed
        DisposableEffect(key1 = Unit) {
            // Dispose the ExoPlayer when the Composable is disposed
            onDispose {
                exoPlayer.release()
            }
        }

        AndroidView(
            modifier = modifier,
            factory = {
                playerView
            }
        )
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun ShowVideoPreview() {
    VideoPlayer(
        playerController = PlayerControllerImpl(),
    ) {

    }
}
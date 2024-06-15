package com.gilbertohdz.player.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.gilbertohdz.player.api.PlayerController
import com.gilbertohdz.player.api.PlayerState
import com.gilbertohdz.player.api.internal.controllers.PlayerControllerImpl
import com.gilbertohdz.player.api.internal.handlers.TickTimeHandler
import com.gilbertohdz.player.api.internal.helpers.toMediaItem
import com.gilbertohdz.player.api.internal.listeners.PlayerListener
import com.gilbertohdz.player.api.media.playlist.PlaylistItem
import com.gilbertohdz.player.utils.logs.appLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(

): ViewModel() {

    private val playerListener by lazy { PlayerListener(this@PlayerViewModel) }
    private val tickTimeHandler by lazy { TickTimeHandler(this@PlayerViewModel) }

    var showControls by mutableStateOf(false)
    var isPlaying by mutableStateOf(false)
    var currentPosition by mutableIntStateOf(0)
    var duration by mutableIntStateOf(0)
    var bufferedPercentage by mutableIntStateOf(0)
    var playerState: PlayerState by mutableStateOf(PlayerState.BUFFERING)

    init {
        appLog("PlayerViewModel", "init")
    }

    internal fun buildExoPlayer(context: Context, playerController: PlayerControllerImpl) : ExoPlayer {
        val playerConfig = playerController.playerConfig
        val playlist = PlaylistItem(
            file = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            title = "Bunny",
            cover = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            mediaId = "1"
        )

        val mediaItems = arrayListOf<MediaItem>()
        mediaItems.add(playlist.toMediaItem())

        return ExoPlayer.Builder(context).build().apply {
            this.setMediaItems(mediaItems)
            this.prepare()
            this.playWhenReady = playerConfig.autoStart
            this.addListener(playerListener)
        }.also { exoPlayer ->
            playerController.exoPlayer = exoPlayer
            tickTimeHandler.startUpdating(exoPlayer)
        }
    }

    internal fun handleLifecycle(player: PlayerController): LifecycleEventObserver {
        return LifecycleEventObserver { source, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    // Start playing when the Composable is in the foreground
                    if (player.isPlaying.not()) {
                        player.play()
                    }
                }
                Lifecycle.Event.ON_STOP -> {
                    // Pause the player when the Composable is in the background
                    player.pause()
                }

                else -> {
                    // Nothing
                }
            }
        }
    }
}

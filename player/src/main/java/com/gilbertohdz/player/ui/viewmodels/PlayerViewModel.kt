package com.gilbertohdz.player.ui.viewmodels

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.gilbertohdz.player.api.PlayerController
import com.gilbertohdz.player.api.internal.controllers.PlayerControllerImpl
import com.gilbertohdz.player.api.internal.helpers.toMediaItem
import com.gilbertohdz.player.api.media.playlist.PlaylistItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(

): ViewModel() {

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

            playerController.exoPlayer = this
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

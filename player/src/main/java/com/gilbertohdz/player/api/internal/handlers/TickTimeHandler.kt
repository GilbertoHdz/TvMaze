package com.gilbertohdz.player.api.internal.handlers

import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.gilbertohdz.player.ui.viewmodels.PlayerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class TickTimeHandler(
    private val viewModel: PlayerViewModel
)  {
    private val updateInterval: Long = 1000 // Update every second

    fun startUpdating(exoPlayer: ExoPlayer) {
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            while (true) {
                viewModel.currentPosition = (exoPlayer.currentPosition / 1000).toInt()
                viewModel.duration = (exoPlayer.duration / 1000).toInt()
                delay(updateInterval)
            }
        }
    }
}
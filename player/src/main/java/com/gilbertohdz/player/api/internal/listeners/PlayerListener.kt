package com.gilbertohdz.player.api.internal.listeners

import androidx.media3.common.Player
import androidx.media3.common.Timeline
import com.gilbertohdz.player.api.PlayerState
import com.gilbertohdz.player.ui.viewmodels.PlayerViewModel
import com.gilbertohdz.player.utils.logs.appLog

internal class PlayerListener(
    private val viewModel: PlayerViewModel
): Player.Listener {

    override fun onEvents(player: Player, events: Player.Events) {
        super.onEvents(player, events)
        appLog("PlayerListener", """onEvents[
            isPlaying: ${player.isPlaying}
            bufferedPercentage: ${player.bufferedPercentage}
        ]""".trimMargin())
        viewModel.isPlaying = player.isPlaying
        viewModel.bufferedPercentage = player.bufferedPercentage
        playbackState(player.playbackState)
    }

    private fun playbackState(@Player.State playbackState: Int) {
        viewModel.playerState = when (playbackState) {
            Player.STATE_BUFFERING -> PlayerState.BUFFERING
            Player.STATE_ENDED -> PlayerState.ENDED
            Player.STATE_IDLE -> PlayerState.IDLE
            Player.STATE_READY -> PlayerState.READY
            else -> {
                if (viewModel.isPlaying) PlayerState.PLAYING else PlayerState.PAUSED
            }
        }
    }

    override fun onTimelineChanged(timeline: Timeline, reason: Int) {
        super.onTimelineChanged(timeline, reason)
    }
}
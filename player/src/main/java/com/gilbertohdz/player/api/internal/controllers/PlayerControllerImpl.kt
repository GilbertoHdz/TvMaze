package com.gilbertohdz.player.api.internal.controllers

import androidx.media3.exoplayer.ExoPlayer
import com.gilbertohdz.player.api.PlayerController
import com.gilbertohdz.player.api.configuration.PlayerConfig

internal class PlayerControllerImpl : PlayerController {

    lateinit var playerConfig: PlayerConfig
    var exoPlayer: ExoPlayer? = null

    override val isPlaying: Boolean = exoPlayer?.isPlaying ?: false

    override fun setup(_playerConfig: PlayerConfig) {
        playerConfig = _playerConfig
    }

    override fun play() {
        exoPlayer?.play()
    }

    override fun pause() {
        exoPlayer?.pause()
    }

    override fun fullscreen() {
        TODO("Not yet implemented")
    }
}
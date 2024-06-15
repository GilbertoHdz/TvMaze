package com.gilbertohdz.player.api.internal.controllers

import androidx.media3.exoplayer.ExoPlayer
import com.gilbertohdz.player.api.PlayerController
import com.gilbertohdz.player.api.configuration.PlayerConfig
import com.gilbertohdz.player.utils.logs.appLog

internal class PlayerControllerImpl : PlayerController {

    lateinit var playerConfig: PlayerConfig
    var exoPlayer: ExoPlayer? = null

    init {
        appLog("PlayerControllerImpl", "init")
    }

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

    override fun forward() {

    }

    override fun rewind() {

    }

    override fun fullscreen() {
        TODO("Not yet implemented -> Only Mobile Ui")
    }
}
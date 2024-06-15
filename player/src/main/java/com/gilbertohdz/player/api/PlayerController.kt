package com.gilbertohdz.player.api

import com.gilbertohdz.player.api.configuration.PlayerConfig

interface PlayerController {
    val isPlaying: Boolean
    fun setup(playerConfig: PlayerConfig)
    fun play()
    fun pause()
    fun fullscreen()
}
package com.gilbertohdz.player.api

sealed class PlayerState {
    data object IDLE : PlayerState() // Mean stop
    data object BUFFERING : PlayerState()
    data object READY : PlayerState()
    data object PLAYING : PlayerState()
    data object PAUSED : PlayerState()
    data object ENDED : PlayerState()
    data object ERROR : PlayerState()
}
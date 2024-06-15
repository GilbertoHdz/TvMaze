package com.gilbertohdz.player.api

import com.gilbertohdz.player.api.internal.controllers.PlayerControllerImpl

object PlayerControllerFactory {
    fun createPlayerController(): PlayerController {
        return PlayerControllerImpl()
    }
}
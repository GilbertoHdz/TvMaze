package com.gilbertohdz.tvmaze.compose.ui.screens.player

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gilbertohdz.player.api.PlayerController
import com.gilbertohdz.player.api.PlayerControllerFactory
import com.gilbertohdz.player.api.configuration.PlayerConfig
import com.gilbertohdz.player.ui.views.VideoPlayer

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    playerController: PlayerController = PlayerControllerFactory.createPlayerController(),
) {
    val playerConfig = PlayerConfig()
    playerController.setup(playerConfig)

    VideoPlayer(
        playerController = playerController,
    ) {
        TvPlayerControls(
            modifier = Modifier
                .fillMaxWidth(),
            playerController = playerController
        )
    }
}
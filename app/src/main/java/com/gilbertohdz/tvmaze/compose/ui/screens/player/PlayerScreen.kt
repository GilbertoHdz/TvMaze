package com.gilbertohdz.tvmaze.compose.ui.screens.player

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.gilbertohdz.player.api.PlayerController
import com.gilbertohdz.player.api.PlayerControllerFactory
import com.gilbertohdz.player.api.configuration.PlayerConfig
import com.gilbertohdz.player.ui.views.VideoPlayer

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
    ExperimentalTvMaterial3Api::class
)
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
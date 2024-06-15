package com.gilbertohdz.tvmaze.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.gilbertohdz.player.api.PlayerControllerFactory
import com.gilbertohdz.player.api.configuration.PlayerConfig
import com.gilbertohdz.player.ui.views.VideoPlayer
import com.gilbertohdz.tvmaze.compose.ui.components.ConnectivityStatus
import com.gilbertohdz.tvmaze.compose.ui.screens.player.PlayerScreen
import com.gilbertohdz.tvmaze.compose.ui.theme.TvMazeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class TvActivity : ComponentActivity() {

    @OptIn(ExperimentalTvMaterial3Api::class, ExperimentalComposeUiApi::class,
        ExperimentalCoroutinesApi::class, ExperimentalAnimationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TvMazeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ConnectivityStatus {
                        PlayerScreen()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TvMazeTheme {
        Greeting("Android")
    }
}
package com.gilbertohdz.tvmaze.compose.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ButtonScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun PlayButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    scale: ButtonScale = ButtonDefaults.scale(),
) {
    ButtonWithIcon(
        icon = Icons.Outlined.PlayArrow,
        label = "Play",
        onClick = onClick,
        modifier = modifier,
        scale = scale
    )
}
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun PreviousButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            Icons.Default.SkipPrevious,
            contentDescription = "label_previous_episode"
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun NextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            Icons.Default.SkipNext,
            contentDescription = "label_next_episode"
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun PlayPauseButton(
    isPlaying: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (icon, description) = if (isPlaying) {
        Icons.Default.Pause to "label_pause"
    } else {
        Icons.Default.PlayArrow to "label_play"
    }
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(icon, description, modifier = Modifier.size(48.dp))
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun RewindButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            Icons.Default.Replay10,
            contentDescription = "label_rewind"
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun SkipButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            Icons.Default.Forward10,
            contentDescription = "label_skip"
        )
    }
}
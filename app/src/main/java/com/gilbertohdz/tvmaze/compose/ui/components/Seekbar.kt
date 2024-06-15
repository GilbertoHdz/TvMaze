package com.gilbertohdz.tvmaze.compose.ui.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

import java.time.Duration

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun Seekbar(
    currentPosition: Int,
    duration: Int,
    modifier: Modifier = Modifier,
    onMoveLeft: () -> Unit = {},
    onMoveRight: () -> Unit = {},
    knobSize: Dp = 8.dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    color: Color = MaterialTheme.colorScheme.onSurface,
) {

    val brush = SolidColor(color)
    val isFocused by interactionSource.collectIsFocusedAsState()
    val outlineSize = knobSize * 1.5f

    Box(
        modifier
            .drawWithCache {
                onDrawBehind {
                    val knobRadius = knobSize.toPx() / 2

                    val start = Offset.Zero.copy(y = knobRadius)
                    val end = start.copy(x = size.width)

                    val knobCenter = start.copy(
                        x = currentPosition.toFloat() / duration.toFloat() * size.width
                    )
                    drawLine(
                        brush, start, end,
                    )
                    if (isFocused) {
                        val outlineColor = color.copy(alpha = 0.6f)
                        drawCircle(outlineColor, outlineSize.toPx() / 2, knobCenter)
                    }
                    drawCircle(brush, knobRadius, knobCenter)
                }
            }
            .height(outlineSize)
            .focusable(true, interactionSource)
            .onKeyEvent {
                when {
                    it.type == KeyEventType.KeyUp && it.key == Key.DirectionLeft -> {
                        onMoveLeft()
                        true
                    }

                    it.type == KeyEventType.KeyUp && it.key == Key.DirectionRight -> {
                        onMoveRight()
                        true
                    }

                    else -> false
                }
            }
    )
}

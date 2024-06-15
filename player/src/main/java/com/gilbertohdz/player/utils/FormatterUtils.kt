package com.gilbertohdz.player.utils

import java.util.Formatter
import java.util.Locale

fun stringForTime(timeMs: Int): String {
    val totalSeconds = timeMs
    val seconds = totalSeconds % 60
    val minutes = (totalSeconds / 60) % 60
    val hours = totalSeconds / 3600

    val formatBuilder = StringBuilder()
    val formatter = Formatter(formatBuilder, Locale.getDefault())

    return if (hours > 0) {
        formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
    } else {
        formatter.format("%02d:%02d", minutes, seconds).toString()
    }
}
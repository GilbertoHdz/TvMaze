package com.gilbertohdz.player.api.configuration

import com.gilbertohdz.player.api.media.playlist.PlaylistItem

data class PlayerConfig(
    val autoStart: Boolean = false,
    val mute: Boolean = false,
    val image: String? = null,
    val playlist: List<PlaylistItem> = emptyList(),
    val playlistUrl: String? = null,
    val playlistIndex: Int = 0,
)
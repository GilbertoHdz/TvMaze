package com.gilbertohdz.player.api.internal.helpers

import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.gilbertohdz.player.api.media.playlist.PlaylistItem

internal fun PlaylistItem.toMediaItem(): MediaItem{
    return MediaItem.Builder()
        .setUri(file)
        .setMediaId(mediaId)
        .setTag(this)
        .setMediaMetadata(MediaMetadata.Builder().setDisplayTitle(title).build())
        .build()
}
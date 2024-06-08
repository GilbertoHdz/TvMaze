package com.gilbertohdz.data.mapper

import com.gilbertohdz.data.remote.dto.EpisodeDto
import com.gilbertohdz.domain.model.Episode
import com.gilbertohdz.domain.model.Image
import com.gilbertohdz.domain.model.Rating

fun EpisodeDto.toEpisode(): Episode? {
    return Episode(
        id = id,
        name = name,
        season = season,
        number = number,
        type = type,
        rating = Rating(rating.average),
        image = image?.let { Image(medium = it.medium, original = it.original) },
        summary = summary.orEmpty()
    )
}

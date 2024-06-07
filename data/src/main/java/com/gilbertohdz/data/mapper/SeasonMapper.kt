package com.gilbertohdz.data.mapper

import com.gilbertohdz.data.remote.dto.SeasonDto
import com.gilbertohdz.domain.model.Image
import com.gilbertohdz.domain.model.Season


fun SeasonDto.toSeason(): Season? {
    return Season(
        id = id,
        name = name,
        number = number,
        episodeOrder = episodeOrder,
        premierDate = premierDate,
        endDate = endDate,
        summary = summary,
        image = Image(medium = image.medium, original = image.original)
    )
}
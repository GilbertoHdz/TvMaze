package com.gilbertohdz.data.mapper

import com.gilbertohdz.data.remote.dto.SearchDto
import com.gilbertohdz.data.remote.dto.ShowDto
import com.gilbertohdz.domain.model.Image
import com.gilbertohdz.domain.model.Rating
import com.gilbertohdz.domain.model.Show

fun ShowDto.toShow(): Show? {
    return Show(
        id = id,
        name = name,
        type = type,
        premiered = premiered,
        ended = ended,
        rating = Rating(rating.average),
        language = language,
        genres = genres,
        image = Image(medium = image.medium, original = image.original),
        summary = summary
    )
}

fun SearchDto.toShow(): Show? {
    return Show(
        id = show.id,
        name = show.name,
        type = show.type,
        premiered = show.premiered,
        ended = show.ended,
        rating = Rating(show.rating.average),
        language = show.language,
        genres = show.genres,
        image = Image(medium = show.image.medium, original = show.image.original),
        summary = show.summary
    )
}
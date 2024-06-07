package com.gilbertohdz.data.mapper

import com.gilbertohdz.data.remote.dto.EmbeddedModel
import com.gilbertohdz.data.remote.dto.ImageModel
import com.gilbertohdz.data.remote.dto.ShowDto
import com.gilbertohdz.domain.model.Image
import com.gilbertohdz.domain.model.Show

fun ShowDto.toShow(): Show? {
    return Show(
        id = id,
        name = name,
        type = type,
        language = language,
        genres = genres,
        image = Image(medium = image.medium, original = image.original),
        summary = summary
    )
}
package com.gilbertohdz.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowDto(
    val id: Int,
    val name: String,
    val type: String,
    val language: String,
    val premiered: String?,
    val ended: String?,
    val rating: RatingModel,
    val genres: List<String>,
    val image: ImageModel,
    val summary: String,
    val embedded: EmbeddedModel?
)
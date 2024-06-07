package com.gilbertohdz.data.remote.dto

data class EpisodeModel(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val type: String,
    val rating: RatingModel,
    val image: ImageModel,
    val summary: String
)
package com.gilbertohdz.data.remote.dto

data class SeasonDto(
    val id: Int,
    val name: String,
    val number: Int,
    val episodeOrder: Int,
    val premierDate: String,
    val endDate: String,
    val summary: String,
    val image: ImageModel
)
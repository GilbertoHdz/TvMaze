package com.gilbertohdz.domain.model

data class Season(
    val id: Int,
    val name: String,
    val number: Int,
    val episodeOrder: Int,
    val premierDate: String,
    val endDate: String,
    val summary: String,
    val image: Image
)
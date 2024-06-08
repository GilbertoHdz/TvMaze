package com.gilbertohdz.domain.model

data class Episode(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val type: String,
    val rating: Rating,
    val image: Image?,
    val summary: String,
)
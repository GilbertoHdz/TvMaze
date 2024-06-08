package com.gilbertohdz.domain.model

data class Show (
    val id: Int,
    val name: String,
    val type: String,
    val premiered: String?,
    val ended: String?,
    val rating: Rating,
    val language: String,
    val genres: List<String>,
    val image: Image?,
    val summary: String,
)
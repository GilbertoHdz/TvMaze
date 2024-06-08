package com.gilbertohdz.domain.use_case

data class TvMazeUseCases(
    val getShowById: GetShowById,
    val getShowsByPage: GetShowsByPage,
    val getShowsBySearch: GetShowsBySearch,
    val getEpisodesById: GetEpisodesById
)
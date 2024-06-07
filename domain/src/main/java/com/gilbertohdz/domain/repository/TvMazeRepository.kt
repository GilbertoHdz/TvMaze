package com.gilbertohdz.domain.repository

import com.gilbertohdz.domain.model.Show

interface TvMazeRepository {

    suspend fun getShowsPerPage(page: Int): Result<List<Show>>
}
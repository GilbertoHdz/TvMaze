package com.gilbertohdz.domain.repository

import com.gilbertohdz.domain.model.Episode
import com.gilbertohdz.domain.model.Season
import com.gilbertohdz.domain.model.Show

interface TvMazeRepository {

    suspend fun getShowsByPage(page: Int): Result<List<Show>>

    suspend fun getShowsBySearch(query: String): Result<List<Show>>

    suspend fun getSeasons(showId: Int): Result<List<Season>>

    suspend fun getEpisodes(showId: Int): Result<List<Episode>>

}
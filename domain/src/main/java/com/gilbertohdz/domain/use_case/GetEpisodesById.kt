package com.gilbertohdz.domain.use_case

import com.gilbertohdz.domain.model.Episode
import com.gilbertohdz.domain.repository.TvMazeRepository

class GetEpisodesById(
    private val repository: TvMazeRepository
) {

    suspend operator fun invoke(
        showId: Int
    ): Result<List<Episode>> {
        return repository.getEpisodes(showId = showId)
    }
}
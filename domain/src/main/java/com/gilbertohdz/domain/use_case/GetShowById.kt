package com.gilbertohdz.domain.use_case

import com.gilbertohdz.domain.model.Show
import com.gilbertohdz.domain.repository.TvMazeRepository

class GetShowById(
    private val repository: TvMazeRepository
) {

    suspend operator fun invoke(
        showId: Int
    ): Result<Show> {
        return repository.getShowsById(showId = showId)
    }
}
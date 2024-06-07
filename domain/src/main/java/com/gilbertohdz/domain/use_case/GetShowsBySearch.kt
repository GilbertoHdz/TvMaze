package com.gilbertohdz.domain.use_case

import com.gilbertohdz.domain.model.Show
import com.gilbertohdz.domain.repository.TvMazeRepository

class GetShowsBySearch(
    private val repository: TvMazeRepository
) {

    suspend operator fun invoke(
        query: String = ""
    ): Result<List<Show>> {
        return repository.getShowsBySearch(query)
    }
}
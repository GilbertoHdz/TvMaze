package com.gilbertohdz.domain.use_case

import com.gilbertohdz.domain.model.Show
import com.gilbertohdz.domain.repository.TvMazeRepository

class GetShowsByPage(
    private val repository: TvMazeRepository
) {

    suspend operator fun invoke(
        page: Int = 1
    ): Result<List<Show>> {
        return repository.getShowsByPage(page)
    }
}
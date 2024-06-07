package com.gilbertohdz.data.repository

import com.gilbertohdz.data.mapper.toShow
import com.gilbertohdz.data.remote.TvMazeApi
import com.gilbertohdz.domain.model.Show
import com.gilbertohdz.domain.repository.TvMazeRepository

class TvMazeRepositoryImpl(
    private val api: TvMazeApi
) : TvMazeRepository {

    override suspend fun getShowsPerPage(page: Int): Result<List<Show>> {
        return try {
            val showDto = api.showsByPage(page)

            Result.success(
                showDto.mapNotNull { it.toShow() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
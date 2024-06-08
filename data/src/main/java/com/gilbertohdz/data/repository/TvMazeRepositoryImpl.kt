package com.gilbertohdz.data.repository

import com.gilbertohdz.data.mapper.toEpisode
import com.gilbertohdz.data.mapper.toSeason
import com.gilbertohdz.data.mapper.toShow
import com.gilbertohdz.data.remote.TvMazeApi
import com.gilbertohdz.domain.model.Episode
import com.gilbertohdz.domain.model.Season
import com.gilbertohdz.domain.model.Show
import com.gilbertohdz.domain.repository.TvMazeRepository

class TvMazeRepositoryImpl(
    private val api: TvMazeApi
) : TvMazeRepository {

    override suspend fun getShowsById(showId: Int): Result<Show> {
        return try {
            val showDto = api.getShowById(showId)
            Result.success(showDto.toShow() ?: error("Show by id: $showId should not be null"))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getShowsByPage(page: Int): Result<List<Show>> {
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

    override suspend fun getShowsBySearch(query: String): Result<List<Show>> {
        return try {
            val showDto = api.showsBySearch(query)

            Result.success(
                showDto.mapNotNull { it.toShow() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getSeasons(showId: Int): Result<List<Season>> {
        return try {
            val seasonDto = api.getSeasons(showId)

            Result.success(
                seasonDto.mapNotNull { it.toSeason() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getEpisodes(showId: Int): Result<List<Episode>> {
        return try {
            val episodeDto = api.getEpisodes(showId)

            Result.success(
                episodeDto.mapNotNull { it.toEpisode() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
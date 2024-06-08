package com.gilbertohdz.data.remote

import com.gilbertohdz.data.remote.dto.EpisodeDto
import com.gilbertohdz.data.remote.dto.SearchDto
import com.gilbertohdz.data.remote.dto.SeasonDto
import com.gilbertohdz.data.remote.dto.ShowDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApi {

    @GET("shows/{show}")
    suspend fun getShowById(
        @Path("show") showId: Int,
    ): ShowDto

    @GET("shows")
    suspend fun showsByPage(
        @Query("page") page: Int
    ): List<ShowDto>

    @GET("search/shows")
    suspend fun showsBySearch(
        @Query("q") query: String
    ): List<SearchDto>

    @GET("shows/{show}/episodes")
    suspend fun getEpisodes(
        @Path("show") showId: Int,
    ): List<EpisodeDto>

    @GET("shows/{show}/seasons")
    suspend fun getSeasons(
        @Path("show") showId: Int,
    ): List<SeasonDto>

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
    }
}
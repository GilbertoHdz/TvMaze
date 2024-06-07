package com.gilbertohdz.data.remote

import com.gilbertohdz.data.remote.dto.ShowDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {

    @GET("shows")
    suspend fun showsByPage(
        @Query("page") page: Int
    ): List<ShowDto>

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
    }
}
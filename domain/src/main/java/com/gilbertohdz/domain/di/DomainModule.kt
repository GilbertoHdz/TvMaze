package com.gilbertohdz.domain.di

import com.gilbertohdz.domain.repository.TvMazeRepository
import com.gilbertohdz.domain.use_case.GetEpisodesById
import com.gilbertohdz.domain.use_case.GetShowById
import com.gilbertohdz.domain.use_case.GetShowsByPage
import com.gilbertohdz.domain.use_case.GetShowsBySearch
import com.gilbertohdz.domain.use_case.TvMazeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideTvMazeUseCases(
        repository: TvMazeRepository
    ): TvMazeUseCases {
        return TvMazeUseCases(
            getShowById = GetShowById(repository = repository),
            getShowsByPage = GetShowsByPage(repository = repository),
            getShowsBySearch = GetShowsBySearch(repository = repository),
            getEpisodesById = GetEpisodesById(repository = repository)
        )
    }
}

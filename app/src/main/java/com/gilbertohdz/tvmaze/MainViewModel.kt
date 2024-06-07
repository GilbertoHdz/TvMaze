package com.gilbertohdz.tvmaze

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilbertohdz.domain.use_case.TvMazeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tvMazeUseCases: TvMazeUseCases
): ViewModel() {

    fun executeInitialState() {
        viewModelScope.launch {
            tvMazeUseCases.getShowsPerPage()
                .onSuccess { shows ->
                    Log.i("MainViewModel", "shows: ${shows.size}")
                }
                .onFailure { e ->
                    e.printStackTrace()
                }
        }
    }
}
package com.hitech.pickit.media.presentation.screens.paging.main.movie

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Similar
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.presentation.screens.paging.main.BaseMainPagingViewModel
import com.hitech.pickit.navigation.MainDestinations.TMDB_SIMILAR_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(
    @Similar repository: BasePagingRepository<Movie>,
    savedStateHandle: SavedStateHandle,
) : BaseMainPagingViewModel<Movie>(repository, savedStateHandle[TMDB_SIMILAR_ID])

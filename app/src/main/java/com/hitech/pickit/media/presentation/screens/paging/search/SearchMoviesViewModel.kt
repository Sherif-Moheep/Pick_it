package com.hitech.pickit.media.presentation.screens.paging.search

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Search
import com.hitech.pickit.media.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel
@Inject
constructor(
    @Search repository: BasePagingRepository<Movie>,
    savedStateHandle: SavedStateHandle,
) : BaseSearchPagingViewModel<Movie>(repository, savedStateHandle)

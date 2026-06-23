package com.hitech.pickit.media.presentation.screens.paging.search

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Search
import com.hitech.pickit.media.domain.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchTVSeriesViewModel
@Inject
constructor(
    @Search repository: BasePagingRepository<TVShow>,
    savedStateHandle: SavedStateHandle,
) : BaseSearchPagingViewModel<TVShow>(repository, savedStateHandle)

package com.hitech.pickit.media.presentation.screens.paging.main.tvshow


import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Discover
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.presentation.screens.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverTvSeriesViewModel @Inject constructor(@Discover repository: BasePagingRepository<TVShow>) :
    BaseMainPagingViewModel<TVShow>(repository)

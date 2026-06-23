package com.hitech.pickit.media.presentation.screens.paging.main.tvshow


import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Trending
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.presentation.screens.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingTvSeriesViewModel @Inject constructor(@Trending repository: BasePagingRepository<TVShow>) :
    BaseMainPagingViewModel<TVShow>(repository)

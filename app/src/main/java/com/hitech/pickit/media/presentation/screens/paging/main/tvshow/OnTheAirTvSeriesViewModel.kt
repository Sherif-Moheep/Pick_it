package com.hitech.pickit.media.presentation.screens.paging.main.tvshow


import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Latest
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.presentation.screens.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnTheAirTvSeriesViewModel @Inject constructor(@Latest repository: BasePagingRepository<TVShow>) :
    BaseMainPagingViewModel<TVShow>(repository)

package com.hitech.pickit.media.presentation.screens.paging.main.tvshow


import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Popular
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.presentation.screens.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularTvSeriesViewModel @Inject constructor(@Popular repository: BasePagingRepository<TVShow>) :
    BaseMainPagingViewModel<TVShow>(repository)

package com.hitech.pickit.media.presentation.screens.paging.main.movie


import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Trending
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.presentation.screens.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(@Trending repository: BasePagingRepository<Movie>) :
    BaseMainPagingViewModel<Movie>(repository)

package com.hitech.pickit.media.presentation.screens.paging.main.movie


import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.TopRated
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.presentation.screens.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(@TopRated repository: BasePagingRepository<Movie>) :
    BaseMainPagingViewModel<Movie>(repository)

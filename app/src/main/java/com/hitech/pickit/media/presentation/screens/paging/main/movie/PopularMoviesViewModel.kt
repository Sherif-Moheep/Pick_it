package com.hitech.pickit.media.presentation.screens.paging.main.movie


import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.di.Popular
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.presentation.screens.paging.main.BaseMainPagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(@Popular repository: BasePagingRepository<Movie>) :
    BaseMainPagingViewModel<Movie>(repository)

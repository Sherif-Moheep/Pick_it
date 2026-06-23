package com.hitech.pickit.media.presentation.screens.feed


import com.hitech.pickit.media.domain.repository.BaseFeedRepository
import com.hitech.pickit.media.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFeedViewModel @Inject constructor(repository: BaseFeedRepository<Movie>) :
    BaseFeedViewModel<Movie>(repository)

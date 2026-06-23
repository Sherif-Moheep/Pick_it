package com.hitech.pickit.media.presentation.screens.feed

import com.hitech.pickit.media.domain.repository.BaseFeedRepository
import com.hitech.pickit.media.domain.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowFeedViewModel @Inject constructor(repository: BaseFeedRepository<TVShow>) :
    BaseFeedViewModel<TVShow>(repository)

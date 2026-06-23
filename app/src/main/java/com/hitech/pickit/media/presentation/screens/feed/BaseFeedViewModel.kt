package com.hitech.pickit.media.presentation.screens.feed

import com.hitech.pickit.media.domain.repository.BaseFeedRepository
import com.hitech.pickit.media.domain.model.FeedWrapper
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.models.base.TMDbViewModel

open class BaseFeedViewModel<T : TMDbItem>(repository: BaseFeedRepository<T>) :
    TMDbViewModel<List<FeedWrapper>>(repository)

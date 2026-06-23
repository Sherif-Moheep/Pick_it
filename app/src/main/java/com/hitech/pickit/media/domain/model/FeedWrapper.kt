package com.hitech.pickit.media.domain.model

import androidx.annotation.StringRes
import com.hitech.pickit.media.presentation.models.TMDbItem

class FeedWrapper(val feeds: List<TMDbItem>, @StringRes val sortTypeResourceId: Int, val sortType: SortType)

package com.hitech.pickit.media.presentation.screens.paging

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hitech.pickit.media.presentation.models.TMDbItem
import kotlinx.coroutines.flow.Flow

abstract class BasePagingViewModel<T : TMDbItem> : ViewModel() {
    abstract val pagingDataFlow: Flow<PagingData<T>>
}

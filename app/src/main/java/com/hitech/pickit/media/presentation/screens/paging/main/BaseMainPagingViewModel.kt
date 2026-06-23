package com.hitech.pickit.media.presentation.screens.paging.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.screens.paging.BasePagingViewModel
import kotlinx.coroutines.flow.Flow

open class BaseMainPagingViewModel<T : TMDbItem>(repository: BasePagingRepository<T>, id: Int? = null) :
    BasePagingViewModel<T>() {
    override val pagingDataFlow: Flow<PagingData<T>> =
        repository.fetchResultStream(id = id).cachedIn(viewModelScope)
}

package com.hitech.pickit.media.domain.repository

import com.hitech.pickit.media.data.BasePagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hitech.pickit.media.presentation.models.TMDbItem
import kotlinx.coroutines.flow.Flow

abstract class BasePagingRepository<T : TMDbItem> {
    protected abstract fun pagingSource(query: String?, id: Int?): BasePagingSource<T>

    fun fetchResultStream(query: String? = null, id: Int? = null): Flow<PagingData<T>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        pagingSourceFactory = { pagingSource(query, id) },
    ).flow

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}
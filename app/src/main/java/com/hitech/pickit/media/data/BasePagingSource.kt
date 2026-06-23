package com.hitech.pickit.media.data

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hitech.pickit.R
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.media.presentation.models.TMDbItem

private const val STARTING_PAGE_INDEX = 1
class TMDbException(message: String) : Exception(message)

abstract class BasePagingSource<T : TMDbItem>(
    private val context: Context
) : PagingSource<Int, T>() {

    protected abstract suspend fun fetchItems(page: Int): Result<List<T>, NetworkError>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return when (val result = fetchItems(page)) {

            is Result.Success -> {
                val response = result.data
                LoadResult.Page(
                    data = response,
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (response.isEmpty()) null else page + 1
                )
            }

            is Result.Error -> {
                LoadResult.Error(result.error.toTMDbException(context))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
    }
}


private fun NetworkError.toTMDbException(context: Context): TMDbException {
    val message = when (this) {
        NetworkError.REQUEST_TIMEOUT -> context.getString(R.string.error_request_timeout)
        NetworkError.TOO_MANY_REQUESTS -> context.getString(R.string.too_many_requests)
        NetworkError.NO_INTERNET -> context.getString(R.string.no_internet)
        NetworkError.SERVER_ERROR -> context.getString(R.string.server_error)
        NetworkError.SERIALIZATION -> context.getString(R.string.serialization_error)
        NetworkError.UNKNOW -> context.getString(R.string.unknow_error)
    }
    return TMDbException(message)
}

package com.hitech.pickit.media.data.tvshow.datasource.paging

import com.hitech.pickit.media.data.BasePagingSource
import android.content.Context
import com.hitech.pickit.core.data.networking.safeApiCall
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.media.data.datasource.remote.api.TVShowService
import com.hitech.pickit.media.data.datasource.remote.mappers.asTVShowDomainModel
import com.hitech.pickit.media.domain.model.TVShow

class SimilarTVSeriesPagingSource(context: Context, private val tvShowApi: TVShowService, private val tvId: Int) :
    BasePagingSource<TVShow>(context) {
    override suspend fun fetchItems(page: Int): Result<List<TVShow>, NetworkError > {

        return safeApiCall {
            val response = tvShowApi.fetchSimilarTVSeries(tvId,page)
            response.items.asTVShowDomainModel()
        }
    }
}

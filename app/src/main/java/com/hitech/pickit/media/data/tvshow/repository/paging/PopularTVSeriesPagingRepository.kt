package com.hitech.pickit.media.data.tvshow.repository.paging

import com.hitech.pickit.media.data.BasePagingSource
import android.content.Context
import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.data.tvshow.datasource.paging.PopularTVSeriesPagingSource
import com.hitech.pickit.media.data.datasource.remote.api.TVShowService
import com.hitech.pickit.media.domain.model.TVShow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularTVSeriesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tvShowApi: TVShowService,
) : BasePagingRepository<TVShow>() {
    override fun pagingSource(query: String?, id: Int?): BasePagingSource<TVShow> =
        PopularTVSeriesPagingSource(context, tvShowApi)
}

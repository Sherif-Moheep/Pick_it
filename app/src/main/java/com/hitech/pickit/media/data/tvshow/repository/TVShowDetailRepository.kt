package com.hitech.pickit.media.data.tvshow.repository

import com.hitech.pickit.media.domain.repository.BaseDetailRepository
import com.hitech.pickit.core.di.IoDispatcher
import com.hitech.pickit.media.data.datasource.remote.api.TVShowService
import com.hitech.pickit.media.data.datasource.remote.mappers.asCastDomainModel
import com.hitech.pickit.media.data.datasource.remote.mappers.asCrewDomainModel
import com.hitech.pickit.media.data.datasource.remote.mappers.asDomainModel
import com.hitech.pickit.media.data.datasource.remote.mappers.asTVShowDomainModel
import com.hitech.pickit.media.domain.model.Cast
import com.hitech.pickit.media.domain.model.Crew
import com.hitech.pickit.media.domain.model.TMDbImage
import com.hitech.pickit.media.domain.model.TVShowDetails
import com.hitech.pickit.media.presentation.models.TMDbItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class TVShowDetailRepository @Inject constructor(
    private val tvShowApi: TVShowService,
    //@ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<TVShowDetails>(ioDispatcher) {
    override suspend fun getDetails(id: Int): TVShowDetails = tvShowApi.fetchTVSeriesDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>> {
        val networkCreditWrapper = tvShowApi.fetchTVSeriesCredit(id)
        return Pair(
            networkCreditWrapper.cast.asCastDomainModel(),
            networkCreditWrapper.crew.asCrewDomainModel(),
        )
    }

    override suspend fun getImages(id: Int): List<TMDbImage> = tvShowApi.fetchImages(id).asDomainModel()

    override suspend fun getSimilarItems(id: Int): List<TMDbItem> =
        tvShowApi.fetchSimilarTVSeries(id).items.asTVShowDomainModel()
}

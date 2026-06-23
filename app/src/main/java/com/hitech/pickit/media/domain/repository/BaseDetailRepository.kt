package com.hitech.pickit.media.domain.repository

import com.hitech.pickit.media.domain.model.Cast
import com.hitech.pickit.media.domain.model.Crew
import com.hitech.pickit.media.domain.model.DetailWrapper
import com.hitech.pickit.media.domain.model.TMDbImage
import com.hitech.pickit.media.domain.model.TMDbItemDetails
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.models.base.BaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseDetailRepository<T : TMDbItemDetails>(ioDispatcher: CoroutineDispatcher) :
    BaseRepository<DetailWrapper>(ioDispatcher) {

    protected abstract suspend fun getDetails(id: Int): T
    protected abstract suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>>
    protected abstract suspend fun getImages(id: Int): List<TMDbImage>
    protected abstract suspend fun getSimilarItems(id: Int): List<TMDbItem>

    override suspend fun getSuccessResult(id: Any?): DetailWrapper {

        require(id is Int) { "ID must be an Integer and not null" }

        val detailsDeferred: Deferred<T>
        val creditDeferred: Deferred<Pair<List<Cast>, List<Crew>>>
        val imageDeferred: Deferred<List<TMDbImage>>
        val similarDeferred: Deferred<List<TMDbItem>>

        coroutineScope {
            detailsDeferred = async { getDetails(id) }
            creditDeferred = async { getCredit(id) }
            imageDeferred = async { getImages(id) }
            similarDeferred = async { getSimilarItems(id) }
        }

        val details = detailsDeferred.await()
        val creditWrapper = creditDeferred.await()
        val images = imageDeferred.await()
        val similarItems = similarDeferred.await()

        return DetailWrapper(
            creditWrapper.first,
            creditWrapper.second,
            details,
            images,
            similarItems,
        )
    }
}
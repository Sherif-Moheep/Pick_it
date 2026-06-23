package com.hitech.pickit.media.data.movie.repository

import com.hitech.pickit.media.domain.repository.BaseDetailRepository
import com.hitech.pickit.core.di.IoDispatcher
import com.hitech.pickit.media.data.datasource.remote.mappers.asCastDomainModel
import com.hitech.pickit.media.data.datasource.remote.mappers.asCrewDomainModel
import com.hitech.pickit.media.data.datasource.remote.mappers.asDomainModel
import com.hitech.pickit.media.data.datasource.remote.mappers.asMovieDomainModel
import com.hitech.pickit.media.data.datasource.remote.api.MovieService
import com.hitech.pickit.media.domain.model.Cast
import com.hitech.pickit.media.domain.model.Crew
import com.hitech.pickit.media.domain.model.MovieDetails
import com.hitech.pickit.media.domain.model.TMDbImage
import com.hitech.pickit.media.presentation.models.TMDbItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class MovieDetailRepository @Inject constructor(
    private val movieApi: MovieService,
   // @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<MovieDetails>(ioDispatcher) {
    override suspend fun getDetails(id: Int): MovieDetails = movieApi.fetchMovieDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>> {
        val networkCreditWrapper = movieApi.movieCredit(id)
        return Pair(
            networkCreditWrapper.cast.asCastDomainModel(),
            networkCreditWrapper.crew.asCrewDomainModel(),
        )
    }

    override suspend fun getImages(id: Int): List<TMDbImage> = movieApi.fetchImages(id).asDomainModel()

    override suspend fun getSimilarItems(id: Int): List<TMDbItem> =
        movieApi.fetchSimilarMovies(id).items.asMovieDomainModel()
}

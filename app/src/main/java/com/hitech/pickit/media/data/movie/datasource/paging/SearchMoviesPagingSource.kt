package com.sample.tmdb.data.paging.movie

import com.hitech.pickit.media.data.BasePagingSource
import android.content.Context
import com.hitech.pickit.media.data.datasource.remote.mappers.asMovieDomainModel
import com.hitech.pickit.core.data.networking.safeApiCall
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.media.data.datasource.remote.api.MovieService
import com.hitech.pickit.media.domain.model.Movie

class SearchMoviesPagingSource(
    context: Context,
    private val movieApi: MovieService,
    private val query: String
) :
    BasePagingSource<Movie>(context) {
    override suspend fun fetchItems(page: Int): Result<List<Movie>, NetworkError> {
        return safeApiCall {
            val response = movieApi.searchMovies(page, query)
            response.items.asMovieDomainModel()
        }
    }
}

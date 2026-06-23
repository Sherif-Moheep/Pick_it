package com.hitech.pickit.media.data.movie.datasource.paging

import com.hitech.pickit.media.data.BasePagingSource
import android.content.Context
import com.hitech.pickit.media.data.datasource.remote.mappers.asMovieDomainModel
import com.hitech.pickit.core.data.networking.safeApiCall
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import com.hitech.pickit.media.data.datasource.remote.api.MovieService
import com.hitech.pickit.media.domain.model.Movie

class DiscoverMoviesPagingSource(context: Context, private val movieApi: MovieService) :
    BasePagingSource<Movie>(context) {
    override suspend fun fetchItems(page: Int): Result<List<Movie>, NetworkError > {

            return safeApiCall {
                val response = movieApi.discoverMovies(page)
                response.items.asMovieDomainModel()
            }

    }
}

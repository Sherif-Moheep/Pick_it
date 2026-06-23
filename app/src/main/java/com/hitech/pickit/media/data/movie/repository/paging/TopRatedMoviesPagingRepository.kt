package com.hitech.pickit.media.data.movie.repository.paging

import com.hitech.pickit.media.data.BasePagingSource
import android.content.Context
import com.hitech.pickit.media.domain.repository.BasePagingRepository
import com.hitech.pickit.media.data.movie.datasource.paging.TopRatedMoviesPagingSource
import com.hitech.pickit.media.data.datasource.remote.api.MovieService
import com.hitech.pickit.media.domain.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopRatedMoviesPagingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieApi: MovieService,
) : BasePagingRepository<Movie>() {
    override fun pagingSource(query: String?, id: Int?): BasePagingSource<Movie> =
        TopRatedMoviesPagingSource(context, movieApi)
}

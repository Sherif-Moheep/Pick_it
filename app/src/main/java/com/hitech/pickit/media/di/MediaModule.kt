package com.hitech.pickit.media.di

import com.hitech.pickit.media.domain.repository.BaseDetailRepository
import com.hitech.pickit.media.domain.repository.BaseFeedRepository
import com.hitech.pickit.media.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.media.data.PersonRepository
import com.hitech.pickit.media.data.movie.repository.MovieFeedRepository
import com.hitech.pickit.media.data.movie.repository.FirestoreBookmarkMovieDetailsRepositoryImpl
import com.hitech.pickit.media.data.movie.repository.MovieDetailRepository
import com.hitech.pickit.profile.domain.repository.ProfileRepository
import com.hitech.pickit.profile.data.repository.ProfileRepositoryImpl
import com.hitech.pickit.media.data.tvshow.repository.TVShowFeedRepository
import com.hitech.pickit.media.data.tvshow.repository.FirestoreBookmarkTVShowDetailsRepositoryImpl
import com.hitech.pickit.media.data.tvshow.repository.TVShowDetailRepository
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.domain.model.MovieDetails
import com.hitech.pickit.media.domain.model.Person
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.domain.model.TVShowDetails
import com.hitech.pickit.media.presentation.models.base.BaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MediaModule {

    @Binds
    @Singleton
    abstract fun bindMovieBookmarkRepository(
        impl: FirestoreBookmarkMovieDetailsRepositoryImpl
    ): BookmarkDetailsRepository<Movie>

    @Binds
    @Singleton
    abstract fun bindTVShowBookmarkRepository(
        impl: FirestoreBookmarkTVShowDetailsRepositoryImpl
    ): BookmarkDetailsRepository<TVShow>

    @Binds
    @Singleton
    abstract fun bindPersonRepository(
        impl: PersonRepository
    ): BaseRepository<Person>


    @Binds
    @Singleton
    abstract fun bindMovieDetailRepository(
        impl: MovieDetailRepository
    ): BaseDetailRepository<MovieDetails>

    @Binds
    @Singleton
    abstract fun bindTVShowDetailRepository(
        impl: TVShowDetailRepository
    ): BaseDetailRepository<TVShowDetails>

    @Binds
    @Singleton
    abstract fun bindMovieFeedRepository(
        impl: MovieFeedRepository
    ): BaseFeedRepository<Movie>

    @Binds
    @Singleton
    abstract fun bindTVShowFeedRepository(
        impl: TVShowFeedRepository
    ): BaseFeedRepository<TVShow>
}
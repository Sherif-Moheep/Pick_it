package com.hitech.pickit.media.presentation.screens.detail_screen

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.media.domain.repository.BaseDetailRepository
import com.hitech.pickit.media.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.domain.model.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    bookmarkRepository: BookmarkDetailsRepository<Movie>,
    repository: BaseDetailRepository<MovieDetails>,
    savedStateHandle: SavedStateHandle,
) : BaseDetailViewModel<MovieDetails, Movie>(bookmarkRepository, repository, savedStateHandle)

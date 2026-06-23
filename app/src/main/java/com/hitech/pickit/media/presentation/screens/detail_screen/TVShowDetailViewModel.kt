package com.hitech.pickit.media.presentation.screens.detail_screen

import androidx.lifecycle.SavedStateHandle
import com.hitech.pickit.media.domain.repository.BaseDetailRepository
import com.hitech.pickit.media.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.domain.model.TVShowDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    bookmarkRepository: BookmarkDetailsRepository<TVShow>,
    repository: BaseDetailRepository<TVShowDetails>,
    savedStateHandle: SavedStateHandle,
) : BaseDetailViewModel<TVShowDetails, TVShow>(bookmarkRepository, repository, savedStateHandle)

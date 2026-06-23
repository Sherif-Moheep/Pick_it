package com.hitech.pickit.media.presentation.screens.BOOK_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.media.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.domain.model.TVShow
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val movieRepository: BookmarkDetailsRepository<Movie>,
    private val tvShowRepository: BookmarkDetailsRepository<TVShow>
) : ViewModel() {


    data class BookmarkUiState(
        val movies: List<Movie> = emptyList(),
        val tvShows: List<TVShow> = emptyList(),
        val isLoading: Boolean = false
    )

    private val _uiState = MutableStateFlow(BookmarkUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadBookmarks()
    }

    fun loadBookmarks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val movieBookmarks = movieRepository.getBookmarks()
                val tvShowBookmarks = tvShowRepository.getBookmarks()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movies = movieBookmarks,
                        tvShows = tvShowBookmarks
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
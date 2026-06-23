package com.hitech.pickit.media.presentation.utili

import androidx.compose.runtime.Immutable
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.presentation.models.TMDbUi

@Immutable
data class MovieState(

    val isLoading: Boolean = false,
    val movies: List<TMDbUi> = emptyList(),
    val selectedMovie: Movie? = null,
    val error: String? = null
)
package com.hitech.pickit.media.presentation.util

import com.hitech.pickit.core.domain.utils.NetworkError

sealed interface UiState<out T> {

    object Loading : UiState<Nothing>

    data class Success<T>(val data: T) : UiState<T>

    data class Error(val error: NetworkError) : UiState<Nothing>
}
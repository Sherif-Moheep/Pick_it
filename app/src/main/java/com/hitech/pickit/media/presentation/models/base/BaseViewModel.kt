package com.hitech.pickit.media.presentation.models.base

import com.hitech.pickit.core.domain.utils.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.media.presentation.utili.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

open class BaseViewModel<T>(
    private val repository: BaseRepository<T>,
    private val id: Any? = null
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<UiState<T>>(UiState.Loading)
    val stateFlow: StateFlow<UiState<T>>
        get() = _stateFlow

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _stateFlow.value = UiState.Loading

            repository.getResult(id)
                .map { result ->
                    when (result) {
                        is Result.Success -> UiState.Success(result.data)
                        is Result.Error -> UiState.Error(result.error)
                    }
                }
                .collect { uiState ->
                    _stateFlow.tryEmit(uiState)
                }
        }
    }
}
package com.hitech.pickit.media.presentation.models

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.hitech.pickit.media.data.datasource.remote.mappers.toUserMessage
import com.hitech.pickit.media.presentation.models.base.BaseViewModel
import com.hitech.pickit.media.presentation.utili.UiState
import com.hitech.pickit.media.presentation.utili.components.ErrorScreen

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun <T> Content(viewModel: BaseViewModel<T>, successScreen: @Composable (T) -> Unit) {

    when (val state = viewModel.stateFlow.collectAsState().value) {

        is UiState.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colorScheme.primaryContainer)

        is UiState.Success -> successScreen(state.data)

        is UiState.Error ->
            ErrorScreen(
                message = state.error.toUserMessage(),
                Modifier.fillMaxSize(),
                refresh = viewModel::refresh,
            )
    }
}
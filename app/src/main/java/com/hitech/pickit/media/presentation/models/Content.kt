package com.hitech.pickit.media.presentation.models

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hitech.pickit.media.data.datasource.remote.mappers.toUserMessage
import com.hitech.pickit.media.presentation.models.base.BaseViewModel
import com.hitech.pickit.media.presentation.util.UiState
import com.hitech.pickit.media.presentation.util.components.ErrorScreen

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun <T> Content(
    viewModel: BaseViewModel<T>,
    successScreen: @Composable (T) -> Unit
) {
    when (val state = viewModel.stateFlow.collectAsState().value) {

        is UiState.Loading ->  Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LoadingIndicator(
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(250.dp)
            )
        }

        is UiState.Success -> successScreen(state.data)

        is UiState.Error ->
            ErrorScreen(
                message = state.error.toUserMessage(),
                Modifier.fillMaxSize(),
                refresh = viewModel::refresh,
            )
    }
}
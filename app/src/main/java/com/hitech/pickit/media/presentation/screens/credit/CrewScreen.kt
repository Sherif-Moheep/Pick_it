package com.hitech.pickit.media.presentation.screens.credit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.hitech.pickit.R
import com.hitech.pickit.media.data.datasource.remote.mappers.asActor
import com.hitech.pickit.media.domain.model.TMDbItemDetails
import com.hitech.pickit.media.presentation.models.Actor
import com.hitech.pickit.media.presentation.models.Credit
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.screens.detail_screen.BaseDetailViewModel
import com.hitech.pickit.media.presentation.utili.UiState
import kotlin.collections.emptyList

@Composable
fun CrewScreen(
    viewModel: BaseDetailViewModel<out TMDbItemDetails, out TMDbItem>,
    onUpPress: () -> Unit,
    onPersonClick: (Credit) -> Unit = {}
) {
    val state by viewModel.stateFlow.collectAsState()

    val crewList: List<Actor> = when (val uiState = state) {
        is UiState.Success -> {
            uiState.data.crew.map { it.asActor() }
        }

        else -> emptyList()
    }

    CreditScreen(
        resourceId = R.string.crew,
        upPress = onUpPress,
        onPersonClicked = onPersonClick,
        items = crewList
    )
}
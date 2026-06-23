package com.hitech.pickit.media.presentation.screens.noDataEmptyScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R.string.no_data_found
import com.hitech.pickit.media.presentation.screens.paging.NoDataFoundAnimation
import com.hitech.pickit.media.presentation.utili.Spacing

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    message: String = stringResource(no_data_found)
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        NoDataFoundAnimation(
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = Spacing.large_16)
        )

        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
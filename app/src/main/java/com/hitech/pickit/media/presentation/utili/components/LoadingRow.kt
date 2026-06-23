package com.hitech.pickit.media.presentation.utili.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.utili.Spacing


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoadingRow(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(Spacing.medium_8),
        horizontalArrangement = Arrangement.spacedBy(Spacing.medium_8, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LoadingIndicator(modifier = Modifier.size(Spacing.huge_56), color = MaterialTheme.colorScheme.primaryContainer)
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoadingRowPreview() {
    PickItTheme {
        LoadingRow()
    }
}

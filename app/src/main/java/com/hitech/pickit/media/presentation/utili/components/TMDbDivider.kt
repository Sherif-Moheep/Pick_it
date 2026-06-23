package com.hitech.pickit.media.presentation.utili.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hitech.pickit.core.presentation.theme.PickItTheme

@Composable
fun TMDbDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = DIVIDER_ALPHA),
    thickness: Dp = 1.dp,
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )

}

private const val DIVIDER_ALPHA = 0.12f

@PreviewLightDark
@Composable
private fun DividerPreview() {
    PickItTheme {
        Box(Modifier.size(height = 10.dp, width = 100.dp)) {
            TMDbDivider(Modifier.align(Alignment.Center))
        }
    }
}

package com.hitech.pickit.media.presentation.utili

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun navigationBarPadding(): Dp {
    val density = LocalDensity.current
    return WindowInsets.navigationBars.getBottom(density).toDp().dp
}
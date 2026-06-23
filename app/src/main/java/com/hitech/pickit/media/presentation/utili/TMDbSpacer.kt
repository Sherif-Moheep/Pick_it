package com.hitech.pickit.media.presentation.utili

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TMDbSpacer() {
    Spacer(
        Modifier.windowInsetsTopHeight(
            WindowInsets.statusBars.add(WindowInsets(top = Spacing.huge_56)),
        ),
    )
}

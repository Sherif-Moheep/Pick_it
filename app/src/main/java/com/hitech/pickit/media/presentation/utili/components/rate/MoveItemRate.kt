package com.hitech.pickit.media.presentation.utili.components.rate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hitech.pickit.core.presentation.theme.rateColors
import com.hitech.pickit.media.presentation.utili.Spacing

@Composable
fun movieItemRate(
    rate: Double,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(percent = 50)
    Surface(
        shape = shape,
        shadowElevation = Spacing.mediumLarge_12,
        modifier = modifier,
    ) {
        Text(
            text = rate.toString(),

            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            modifier =
                Modifier
                    .background(Brush.horizontalGradient(Color.rateColors(movieRate = rate)))
                    .padding(horizontal = 10.dp),
        )
    }
}
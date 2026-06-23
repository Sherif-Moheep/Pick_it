package com.hitech.pickit.onboarding.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    onDotClick: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { index ->
            val isSelected = index == selectedIndex

            val color: Color by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                animationSpec = tween(300),
                label = "DotColor"
            )

            val size by animateDpAsState(
                targetValue = if (isSelected) 12.dp else 8.dp,
                animationSpec = tween(300),
                label = "DotSize"
            )

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(size)
                    .clip(CircleShape)
                    .background(color)
                    .clickable { onDotClick(index) }
            )
        }
    }
}
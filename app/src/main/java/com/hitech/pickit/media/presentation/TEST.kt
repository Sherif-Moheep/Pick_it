@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.hitech.pickit.media.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TEST(modifier: Modifier = Modifier) {

    var isScaledDown by remember { mutableStateOf(true) }
    val animationProgress by animateFloatAsState(
        targetValue = if (isScaledDown) 0f else 1f,
        animationSpec = MaterialTheme.motionScheme.defaultSpatialSpec()
    )
    Box(
        modifier = modifier
            .size(100.dp)
            .graphicsLayer {
                scaleX = 1f + animationProgress
                scaleY = 1f + animationProgress
            }
            .background(Color.Green)
            .clickable(onClick = { isScaledDown = !isScaledDown })
    )

}

@Composable
fun SingleGroupButton(modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableStateOf(0) }
    ButtonGroup(
        overflowIndicator = {
            FilledTonalIconButton(
                onClick = {
                    it.show()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.outline_more_vert_24),
                    contentDescription = null
                )

            }
        }

    ) {
        for (i in 0 until 3) {
            val checked = i == selectedIndex
            this.toggleableItem(
                checked = checked,
                label = "Item$i",
                weight = 1f,
                onCheckedChange = { selectedIndex = i },
                icon = if (checked) {
                    {
                        Icon(
                            ImageVector.vectorResource(R.drawable.outline_edit_24),
                            contentDescription = null
                        )
                    }
                } else null
            )
        }

    }

}


















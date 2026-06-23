package com.hitech.pickit.media.presentation.screens.cinemas_screen.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.core.presentation.theme.PickItTheme


@Composable
fun PostBorder(
    modifier: Modifier = Modifier
        .padding(1.dp)
        .clip(RoundedCornerShape(16.dp)),
    cornerRadius: Dp = 16.dp,
    borderWidth: Dp = 1.5.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .drawBehind {
                val borderBrush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.8f),
                        Color.Transparent,
                        Color.White.copy(alpha = 0.8f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, size.height)
                )

                val strokeWidth = borderWidth.toPx()
                val halfStroke = strokeWidth / 2

                drawRoundRect(
                    brush = borderBrush,
                    cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                    style = Stroke(width = strokeWidth),
                    topLeft = Offset(halfStroke, halfStroke),
                    size = Size(size.width - strokeWidth, size.height - strokeWidth)
                )
            }
            .clip(RoundedCornerShape(cornerRadius))
    ) {
        Box(modifier = modifier) {
            content()
        }

    }
}


@PreviewLightDark
@Composable
private fun test() {
    PickItTheme {
        PostBorder(content = {
            Image(
                painter = painterResource(R.drawable.big_hero_poster),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillBounds,
            )
        })
    }

}
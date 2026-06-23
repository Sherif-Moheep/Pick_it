package com.hitech.pickit.media.presentation.utili.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.utili.Spacing

@Composable
fun TMDbCard(
    tmdbItem: TMDbItem,
    onFeedClick: (TMDbItem) -> Unit,
    imageUrl: String? = tmdbItem.posterUrl,
    itemWidth: Dp = Spacing.mega_120,
) {
    Card(
        modifier =
            Modifier
                .padding(Spacing.smallMedium_6)
                .clickable(onClick = { onFeedClick(tmdbItem) }),
        shape = RoundedCornerShape(10.dp),
    ) {
        Box(modifier = Modifier) {

            AsyncImage(
                model = imageUrl,
                contentDescription = tmdbItem.name,
                modifier =
                    Modifier
                        .size(width = itemWidth, height = 180.dp),
                contentScale = ContentScale.Crop,
            )
            Box(
                Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Transparent, Color.Black),
                            startY = 100f
                        )
                    )
                    .size(width = itemWidth, height = 180.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = tmdbItem.name,
                    fontSize = 13.sp,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier =
                        Modifier
                            .align(alignment = Alignment.BottomCenter)
                            .size(width = itemWidth, height = 36.dp)
                            .wrapContentHeight(),
                )
            }

        }
    }
}

@Preview
@Composable
private fun tset() {

    MaterialTheme.colorScheme.primary
    
}
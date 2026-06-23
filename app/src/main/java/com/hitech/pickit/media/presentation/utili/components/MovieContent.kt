package com.hitech.pickit.media.presentation.utili.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.utili.Spacing
import com.hitech.pickit.media.presentation.utili.components.rate.movieItemRate

@Composable
fun TMDbContent(
    movieItem: TMDbItem,
    onClick: (TMDbItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        movieItemRate(
            rate = movieItem.voteAverage,
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .zIndex(2f),
        )

        ElevatedCard(
            modifier =
                Modifier
                    .fillMaxSize()
                    .offset(y = Spacing.mediumLarge_12),
            shape = RoundedCornerShape(size = Spacing.medium_8),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = Spacing.medium_8),
            onClick = { onClick.invoke(movieItem) },
        ) {
            Box {
                movieItemPoster(movieItem.posterUrl, movieItem.name)
                movieItemInfo(
                    movieItem,
                    modifier =
                        Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            //NOTE ROBERT
                            .background(Color.Black.copy(alpha = 0.6f)),
                )
            }
        }
    }
}


@Composable
fun BoxScope.movieItemPoster(posterUrl: String?, movieItemName: String) {


    val placeholderPainter = rememberVectorPainter(Icons.Default.Movie)
    val errorPainter = rememberVectorPainter(Icons.Filled.BrokenImage)


    var imageColorFilter: ColorFilter? by remember { mutableStateOf(null) }

    val tintColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)


    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(posterUrl)
            .crossfade(true)
            .build(),
        contentDescription = movieItemName,
        contentScale = ContentScale.Crop,
        modifier =
            Modifier
                .fillMaxSize()
                .align(Alignment.Center),
        placeholder = placeholderPainter,
        error = errorPainter,

        onLoading = {
            imageColorFilter = ColorFilter.tint(tintColor)
        },
        onSuccess = {
            imageColorFilter = null
        },
        onError = {
            imageColorFilter = ColorFilter.tint(tintColor)
        },

        colorFilter = imageColorFilter
    )
}

@Composable
fun movieItemInfo(movieItem: TMDbItem, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.small_4),
        modifier =
            modifier.padding(
                horizontal = Spacing.smallMedium_6,
                vertical = Spacing.small_4,
            ),
    ) {
        TMDbItemName(name = movieItem.name)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            movieItem.releaseDate?.let { TMDbItemFeature(Icons.Default.DateRange, it) }
            TMDbItemFeature(Icons.Default.ThumbUp, movieItem.voteCount.toString())
        }
    }
}

@Composable
fun TMDbItemName(name: String) = Text(
    text = name,
    style =
        MaterialTheme.typography.titleMedium.copy(
            color = Color.White,
            letterSpacing = 1.5.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.W500,
        ),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
)

@Composable
fun TMDbItemFeature(icon: ImageVector, field: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(13.dp),
        )
        Text(
            text = field,
            style =
                MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    letterSpacing = 1.5.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W400,
                ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = Spacing.extraSmall_2),
        )
    }
}


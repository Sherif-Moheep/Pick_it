package com.hitech.pickit.media.presentation.utili.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.models.TMDbUi
import com.hitech.pickit.media.presentation.screens.BOOK_list.components.MoviePreview
import com.hitech.pickit.media.presentation.utili.Spacing

@Composable
fun MovieCard(
    movieItem: TMDbUi,
    onFeedClick: (TMDbUi) -> Unit,
    imageUrl: String? = movieItem.picture,
    itemWidth: Dp = Spacing.mega_120,
) {
    Card(
        modifier =
            Modifier
                .padding(Spacing.smallMedium_6)
                .clickable(onClick = { onFeedClick(movieItem) }),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = movieItem.name,
                modifier =
                    Modifier
                        .size(width = itemWidth, height = 180.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = movieItem.name,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier =
                    Modifier
                        .size(width = itemWidth, height = 36.dp)
                        .wrapContentHeight(),
            )
        }
    }
}

@Preview
@Composable
private fun Test() {
    PickItTheme {
        MovieCard(
            movieItem = MoviePreview,
            onFeedClick = { },
            imageUrl = MoviePreview.picture
        )
    }

}

package com.hitech.pickit.media.presentation.screens.BOOK_list.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.models.TMDbUi
import com.hitech.pickit.media.presentation.models.toTMDbUi
import com.hitech.pickit.media.presentation.utili.components.rate.StarsRate


@Composable
fun MovieListItem(
    TMDbUi: TMDbUi,
    onclick: () -> Unit,
    screenHeight: Dp,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val imageHeight =
        if (isLandscape) screenHeight * 0.66f
        else screenHeight * 0.5f
    val imageWidth =
        if (isLandscape) screenHeight * 0.5f
        else screenHeight * 0.8f
    Card(
        modifier = modifier
            .background(Color.Transparent)
            .width(imageWidth)
            .padding(15.dp)
            .padding(top = 15.dp),
        elevation = CardDefaults.cardElevation(20.dp),
        shape = RoundedCornerShape(80f),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

            ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 12.dp)
                    .height(imageHeight),
                shape = RoundedCornerShape(80f)

            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${TMDbUi.picture}",
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillBounds,
                )
            }
            Text(
                TMDbUi.name,
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 30.sp)
            )



            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .padding(top = 10.dp)
            ) {
                CategoryItems(
                    categoryList = TMDbUi.category,
                    modifier = Modifier
                        .height(screenHeight * 0.045f)
                        .fillMaxWidth()
                )
            }



            StarsRate(
                rate = TMDbUi.rate,
                starSizeDp = 25,
                modifier = Modifier
                    .height(50.dp)
                    .padding(top = 10.dp)
            )


            Text(
                "25.4 $",
                fontSize = 50.sp,
                style = MaterialTheme.typography.labelLarge
            )


            Button(
                onClick = onclick,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
            ) {
                Text(
                    "Watch Now",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun MovieItemPreview() {
    PickItTheme {
        MovieListItem(
            TMDbUi = MoviePreview,
            onclick = { },
            screenHeight = 900.dp,
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        )
    }

}

internal val MoviePreview = Movie(
    id = 1,
    name = "Joker",
    voteAverage = 8.5,
    genreIds = listOf(35, 16, 28, 27, 878, 10770),
    posterUrl = "TODO()",
    overview = "IT All Aout That U Are See A NONE Real Data So IT Just For Testing",
    releaseDate = "2008-07-18",
    voteCount = 30000,
    backdropUrl = ""
).toTMDbUi()
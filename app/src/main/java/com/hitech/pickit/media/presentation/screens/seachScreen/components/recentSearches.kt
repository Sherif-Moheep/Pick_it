package com.hitech.pickit.media.presentation.screens.seachScreen.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hitech.pickit.R
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.utili.components.MoviePosterAndNameCard

@Composable
fun RecentSearches(
    modifier: Modifier = Modifier,
   recentSearches: List<MoviePoster>, onItemClick: (String) -> Unit
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.recent_searches),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            recentSearches.forEach { movie ->
                MoviePosterAndNameCard(movie = movie) { selectedText ->
                    onItemClick(selectedText)
                }
            }
        }

    }
}

@PreviewLightDark
@Composable
private fun Test() {
    PickItTheme {
        RecentSearches(recentSearches = SearchData.recentSearches, onItemClick = { })
    }

}


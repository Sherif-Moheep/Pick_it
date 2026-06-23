package com.hitech.pickit.media.presentation.utili.components.rate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hitech.pickit.R
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.models.DisplayableNumber
import com.hitech.pickit.media.presentation.screens.BOOK_list.components.MoviePreview

@Composable
fun StarsRate(
    modifier: Modifier = Modifier,
    rate: DisplayableNumber,
    starsCount: Int = 5,
    starSizeDp: Int = 30,
) {

    val rateStar = rate.value/2.0

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        Alignment.CenterVertically
    ) {
        Text(
            rate.formatted,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 25.sp
            )
        )
        Spacer(Modifier.width(12.dp))
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val activeStar = ImageVector.vectorResource(R.drawable.star)
            val halfActiveStar = ImageVector.vectorResource(R.drawable.star_half_empty)
            val notActiveStar = ImageVector.vectorResource(R.drawable.star2)

            for (i in 1..starsCount) {

                when {
                    rateStar >= i -> Icon(
                        imageVector = activeStar,
                        contentDescription = null,
                        tint = Color.Yellow.copy(green = 0.75f),
                        modifier = Modifier.size(starSizeDp.dp)
                    )

                    rateStar >= i.toFloat() - 0.5f -> Icon(
                        imageVector = halfActiveStar,
                        contentDescription = null,
                        tint = Color.Yellow.copy(green = 0.75f),
                        modifier = Modifier.size(starSizeDp.dp)
                    )

                    else -> Icon(
                        imageVector = notActiveStar,
                        contentDescription = null,
                        tint = Color.Yellow.copy(green = 0.75f),
                        modifier = Modifier.size(starSizeDp.dp)
                    )
                }


            }

        }
    }
}



@Preview
@Composable
private fun StarRatePreview() {
    PickItTheme {
        StarsRate(
            rate = MoviePreview.rate
        )
    }

}
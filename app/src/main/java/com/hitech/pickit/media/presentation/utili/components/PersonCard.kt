package com.hitech.pickit.media.presentation.utili.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hitech.pickit.R
import com.hitech.pickit.media.domain.model.Gender
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.models.Actor
import com.hitech.pickit.media.presentation.models.placeholderIcon
import com.hitech.pickit.media.presentation.utili.Spacing

@Composable
fun PersonCard(
    person: Actor,
    onPersonClicked: (person: Actor) -> Unit,
    modifier: Modifier = Modifier,
    testPainter: Painter? = null,
) {
    Column(
        modifier = modifier
            .padding(Spacing.small_4)
            .clickable {
                onPersonClicked.invoke(person)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = Spacing.medium_8),
            modifier = Modifier.size(Spacing.mega_120),
        ) {
            if (testPainter != null) {
                Image(
                    painter = testPainter,
                    contentDescription = stringResource(
                        id = R.string.person_content_description,
                        person.name,
                        person.role,
                    ),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            } else {
                val placeholderPainter = rememberVectorPainter(person.gender.placeholderIcon)

                var imageColorFilter: ColorFilter? by remember { mutableStateOf(null) }
                val tintColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(person.profileUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(
                        id = R.string.person_content_description,
                        person.name,
                        person.role,
                    ),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = placeholderPainter,
                    error = placeholderPainter,
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
        }

        Text(
            text = person.name,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = Spacing.small_4),
        )
        Text(
            text = person.role,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = Spacing.extraSmall_2),
        )
    }
}

@PreviewLightDark
@Composable
private fun Test() {
    PickItTheme {
        Row {
            PersonCard(
                person = Actor(
                    id = 1,
                    name = "First Person",
                    role = "Actor",
                    profileUrl = "",
                    gender = Gender.MALE
                ),
                onPersonClicked = {}
            )

            PersonCard(
                person = Actor(
                    id = 2,
                    name = "Second Person",
                    role = "Director",
                    profileUrl = null,
                    gender = Gender.FEMALE
                ),
                onPersonClicked = {},
            )
        }
    }

}
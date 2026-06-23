package com.hitech.pickit.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hitech.pickit.R
import com.hitech.pickit.core.presentation.theme.PickItTheme

@Composable
fun ProfileScreenGradient(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUrl ?: R.drawable.default_user_img)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .fillMaxSize()
                .blur(2.dp),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter,


            )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface
                        ),
                        startY = 300f,
                        endY = 900f
                    )
                )
        )
    }
}

@Preview
@Composable
private fun ProfileScreenGradientPreview() {
    PickItTheme {
        ProfileScreenGradient(
            imageUrl = null
        )
    }
}

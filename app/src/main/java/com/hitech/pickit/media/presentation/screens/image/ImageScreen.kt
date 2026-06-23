package com.hitech.pickit.media.presentation.screens.image

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hitech.pickit.R
import com.hitech.pickit.media.data.datasource.remote.mappers.toUserMessage
import com.hitech.pickit.media.domain.model.TMDbImage
import com.hitech.pickit.media.domain.model.TMDbItemDetails
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.screens.detail_screen.BaseDetailViewModel
import com.hitech.pickit.media.presentation.util.Spacing
import com.hitech.pickit.media.presentation.util.UiState
import com.hitech.pickit.media.presentation.util.components.ErrorScreen

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)
@Composable
fun ImagesScreen(
    viewModel: BaseDetailViewModel<out TMDbItemDetails, out TMDbItem>,
    initialPage: Int,
    onUpPress: () -> Unit
) {
    val state = viewModel.stateFlow.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Images") },
                navigationIcon = {
                    IconButton(onClick = onUpPress) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (state) {
                is UiState.Loading ->  Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    LoadingIndicator(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(250.dp)
                    )
                }
                is UiState.Error -> ErrorScreen(
                    message = state.error.toUserMessage(),
                    modifier = Modifier.fillMaxSize(),
                    refresh = viewModel::refresh
                )

                is UiState.Success -> {
                    val images = state.data.images

                    if (images.isEmpty() || initialPage !in images.indices) return@Box

                    val pagerState =
                        rememberPagerState(
                            initialPage = initialPage,
                            initialPageOffsetFraction = 0f,
                        ) { images.size }

                    Box {
                        HorizontalPager(
                            state = pagerState,
                            key = { images[it].url + it },
                            beyondViewportPageCount = 4
                        ) {
                            Poster(images[it])
                        }
                        Index(
                            position = pagerState.currentPage + 1,
                            imageCount = pagerState.pageCount
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Poster(image: TMDbImage) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        BlurImage(image.url)

        Card(
            Modifier
                .systemBarsPadding()
                .padding(Spacing.mediumLarge_12)
                .animateContentSize()
                .wrapContentSize(),
            elevation = CardDefaults.cardElevation(defaultElevation = Spacing.large_16),
            shape = RoundedCornerShape(Spacing.mediumLarge_12)
        ) {
            Box {
                AsyncImage(
                    model = image.url,
                    placeholder = rememberVectorPainter(Icons.Default.Image),
                    error = rememberVectorPainter(Icons.Default.BrokenImage),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    contentScale = ContentScale.FillWidth,
                )
                VoteCount(image.voteCount)
            }
        }
    }
}


@Composable
fun BlurImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = stringResource(id = R.string.poster_content_description),
        contentScale = ContentScale.FillHeight,
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .blur(Spacing.large_16),
    )
}

@Composable
private fun BoxScope.VoteCount(voteCount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .wrapContentSize()
                .align(Alignment.BottomStart)
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(
                        bottomStart = Spacing.mediumLarge_12,
                        topEnd = Spacing.mediumLarge_12
                    ),
                )
                .padding(Spacing.small_4),
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(id = R.string.likes_content_description),
            modifier = Modifier.padding(end = Spacing.small_4),
        )
        Text(text = voteCount.toString(), style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun BoxScope.Index(position: Int, imageCount: Int) {
    Text(
        text = "$position / $imageCount",
        style = MaterialTheme.typography.bodyMedium,
        modifier =
            Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(Spacing.small_4)
                .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f))
                .padding(horizontal = Spacing.medium_8, vertical = Spacing.extraSmall_2),
    )
}
package com.hitech.pickit.media.presentation.screens.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hitech.pickit.R
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.utili.Spacing
import com.hitech.pickit.media.presentation.utili.TMDbSpacer
import com.hitech.pickit.media.presentation.utili.components.ErrorScreen
import com.hitech.pickit.media.presentation.utili.components.LoadingRow
import com.hitech.pickit.media.presentation.utili.components.TMDbContent
import com.hitech.pickit.media.presentation.utili.fullSpanGridItem
import com.hitech.pickit.media.presentation.utili.navigationBarPadding

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun <T : TMDbItem> PagingScreen(
    viewModel: com.hitech.pickit.media.presentation.screens.paging.BasePagingViewModel<T>,
    onClick: (TMDbItem) -> Unit
) {
    val lazyTMDbItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    when (lazyTMDbItems.loadState.refresh) {
        is LoadState.Loading -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                LoadingIndicator(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(250.dp)
                )
            }

        }

        is LoadState.Error -> {
            val message =
                (lazyTMDbItems.loadState.refresh as? LoadState.Error)?.error?.message ?: return

            ErrorScreen(
                message = message,
                modifier = Modifier.fillMaxSize(),
                refresh = { lazyTMDbItems.retry() },
            )
        }

        else -> {
            if (lazyTMDbItems.itemCount == 0) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    NoDataFoundAnimation(modifier = Modifier.size(200.dp))
                }
            } else {
                LazyTMDbItemGrid(lazyTMDbItems, onClick)
            }
        }
    }
}

@Composable
private fun <T : TMDbItem> LazyTMDbItemGrid(
    lazyTMDbItems: LazyPagingItems<T>, onClick: (TMDbItem) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding =
        PaddingValues(
            start = Spacing.medium_8,
            end = Spacing.medium_8,
            bottom = navigationBarPadding().plus(Spacing.medium_8),
        ),
        horizontalArrangement =
        Arrangement.spacedBy(
            Spacing.medium_8,
            Alignment.CenterHorizontally,
        ),
        content = {
            fullSpanGridItem {
                TMDbSpacer()
            }

            items(lazyTMDbItems.itemCount) { index ->
                val tmdbItem = lazyTMDbItems[index]
                tmdbItem?.let {
                    TMDbContent(
                        it,
                        onClick,
                       Modifier
                            .height(320.dp)
                            .padding(vertical = Spacing.medium_8),
                    )
                }
            }

            when (lazyTMDbItems.loadState.append) {
                is LoadState.Loading -> {
                    fullSpanGridItem {
                        LoadingRow(modifier = Modifier.padding(vertical = Spacing.medium_8))
                    }
                }

                is LoadState.Error -> {
                    val message =
                        (lazyTMDbItems.loadState.append as? LoadState.Error)?.error?.message
                            ?: return@LazyVerticalGrid

                    fullSpanGridItem {
                        ErrorScreen(
                            message = message,
                            modifier = Modifier.padding(vertical = Spacing.medium_8),
                            refresh = { lazyTMDbItems.retry() },
                        )
                    }
                }

                else -> Unit
            }
        },
    )
}

@Composable
fun NoDataFoundAnimation(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.no_data_found)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}
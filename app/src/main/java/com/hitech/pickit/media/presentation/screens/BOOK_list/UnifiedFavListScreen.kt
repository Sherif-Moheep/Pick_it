@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.hitech.pickit.media.presentation.screens.BOOK_list

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hitech.pickit.media.data.datasource.remote.mappers.toCommonUiModel
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.screens.BOOK_list.components.MovieListItem
import kotlin.math.abs


@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun UnifiedFavListScreen(
    items: List<TMDbItem>,
    isLoading: Boolean,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val itemHeight = if (isLandscape) screenHeight * 0.8f else screenHeight * 0.9f
    val itemWidth = if (isLandscape) screenWidth * 0.26f else screenWidth * 0.9f

    val listState = rememberLazyListState()
    var selectedImage: String? by remember { mutableStateOf("") }

    LaunchedEffect(listState, items) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isEmpty() || items.isEmpty()) return@collect

                val viewportCenter = listState.layoutInfo.viewportEndOffset / 2
                val centerItem = visibleItems.minByOrNull { item ->
                    val itemCenter = item.offset + item.size / 2
                    abs(itemCenter - viewportCenter)
                }

                centerItem?.let {
                    selectedImage = items[it.index].posterUrl
                }
            }
    }

    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
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
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = selectedImage,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, MaterialTheme.colorScheme.surface),
                            startY = 300f,
                            endY = 1200f
                        )
                    )
            )

            LazyRow(
                state = listState,
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                items(
                    items = items,
                    key = { it.id }
                ) { item ->
                    MovieListItem(
                        TMDbUi = item.toCommonUiModel(),
                        onclick = { onItemClick(item.id) },
                        screenHeight = screenHeight,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 50.dp)
                            .height(itemHeight)
                            .width(itemWidth)
                    )
                }
            }
        }
    }
}
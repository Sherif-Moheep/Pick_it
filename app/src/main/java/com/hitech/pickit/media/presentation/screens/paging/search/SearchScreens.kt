package com.hitech.pickit.media.presentation.screens.paging.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hitech.pickit.R
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.screens.paging.PagingScreen
import com.hitech.pickit.media.presentation.screens.searchScreen.components.DeclarativeItems
import com.hitech.pickit.media.presentation.screens.searchScreen.components.RecentSearches
import com.hitech.pickit.media.presentation.screens.searchScreen.components.SearchBar
import com.hitech.pickit.media.presentation.screens.searchScreen.components.SearchData
import com.hitech.pickit.media.presentation.util.components.TMDbDivider
import com.hitech.pickit.navigation.MainDestinations.TMDB_MOVIE_DETAIL_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE

@Composable
fun SearchMoviesScreen(
    navController: NavController,
    viewModel: SearchMoviesViewModel
) {
    Search(
        viewModel = viewModel,
        onClick = { navController.navigate("${TMDB_MOVIE_DETAIL_ROUTE}/${it.id}") },
        upPress = { navController.navigateUp() },
    )
}

@Composable
fun SearchTVSeriesScreen(
    navController: NavController,
    viewModel: SearchTVSeriesViewModel
) {
    Search(
        viewModel = viewModel,
        onClick = { navController.navigate("${TMDB_TV_SHOW_DETAIL_ROUTE}/${it.id}") },
        upPress = { navController.navigateUp() },
    )
}

@Composable
fun <T : TMDbItem> Search(
    viewModel: BaseSearchPagingViewModel<T>,
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var query by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(onClick = upPress) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            SearchBar(
                searchText = query,
                onTextChange = { newText ->
                    query = newText
                    if (newText.isNotEmpty()) {
                        viewModel.showResult(newText)
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }

        TMDbDivider()

        if (query.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                RecentSearches(
                    recentSearches = SearchData.recentSearches,
                    onItemClick = { selectedText ->
                        query = selectedText
                        viewModel.showResult(selectedText)
                    }
                )

                Spacer(Modifier.height(20.dp))

                DeclarativeItems(
                    title = R.string.genres,
                    tags = SearchData.genres,
                    onTagClick = { tag ->
                        query = tag
                        viewModel.showResult(tag)
                    }
                )

                TextButton(onClick = {}) {
                    Text(
                        text = stringResource(R.string.view_more),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontSize = 14.sp
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(Modifier.height(20.dp))

                DeclarativeItems(
                    title = R.string.languages,
                    tags = SearchData.languages,
                    onTagClick = { tag ->
                        query = tag
                        viewModel.showResult(tag)
                    }
                )
            }

        } else {
            viewModel.showResult(query)

            PagingScreen(
                viewModel = viewModel,
                onClick = onClick
            )
        }
    }
}
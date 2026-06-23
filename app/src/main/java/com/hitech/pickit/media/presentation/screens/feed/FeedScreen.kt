package com.hitech.pickit.media.presentation.screens.feed

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hitech.pickit.R
import com.hitech.pickit.media.domain.model.FeedWrapper
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.domain.model.SortType
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.models.Content
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.screens.feed.utils.pagerTransition
import com.hitech.pickit.media.presentation.utili.Spacing
import com.hitech.pickit.media.presentation.utili.TMDbSpacer
import com.hitech.pickit.media.presentation.utili.components.DestinationBar
import com.hitech.pickit.media.presentation.utili.components.TMDbCard
import com.hitech.pickit.navigation.MainDestinations.TMDB_AIRING_TODAY_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_DISCOVER_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_DISCOVER_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_MOVIE_DETAIL_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_NOW_PLAYING_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_ON_THE_AIR_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_POPULAR_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_POPULAR_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_SEARCH_MOVIE_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TOP_RATED_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TOP_RATED_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TRENDING_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TRENDING_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_UPCOMING_MOVIES_ROUTE

@Composable
fun MovieFeedScreen(
    navController: NavController,
    viewModel: MovieFeedViewModel
) {
    FeedScreen(
        viewModel = viewModel,
        navController = navController,
        onSearchClicked = { navController.navigate(TMDB_SEARCH_MOVIE_ROUTE) },
        onClick = { navController.navigate("${TMDB_MOVIE_DETAIL_ROUTE}/${it.id}") },
        R.string.movies,
    )
}

@Composable
fun TVShowFeedScreen(
    navController: NavController,
    viewModel: TVShowFeedViewModel
) {
    FeedScreen(
        viewModel = viewModel,
        navController = navController,
        onSearchClicked = { navController.navigate(TMDB_SEARCH_TV_SHOW_ROUTE) },
        onClick = { navController.navigate("${TMDB_TV_SHOW_DETAIL_ROUTE}/${it.id}") },
        R.string.tv_series,
    )
}

@Composable
private fun <T : TMDbItem> FeedScreen(
    viewModel: BaseFeedViewModel<T>,
    navController: NavController,
    onSearchClicked: () -> Unit,
    onClick: (TMDbItem) -> Unit,
    @StringRes resourceId: Int,
) {
    Content(viewModel = viewModel) { feeds ->
        Box {
            FeedCollectionList(feeds, navController, onClick)
            DestinationBar(
                title =
                    stringResource(
                        R.string.app_title,
                        stringResource(resourceId),
                    ),
                onSearchClicked = onSearchClicked,
            )
        }
    }
}

@Composable
fun FeedCollectionList(
    collection: List<FeedWrapper>,
    navController: NavController,
    onFeedClick: (TMDbItem) -> Unit
) {
    LazyColumn {
        item {
            TMDbSpacer()
        }
        item {
            PagerTMDbItemContainer(
                feedWrapper = collection.first(),
                navController = navController,
                onFeedClick = onFeedClick,
            )
        }
        itemsIndexed(collection.drop(1)) { index, feedCollection ->
            Column(modifier = Modifier.padding(top = Spacing.extraExtraLarge_32)) {
                Header(feedCollection, navController)
                Feeds(feedCollection.feeds, onFeedClick, index)
            }
        }
        item {
            Spacer(
                Modifier
                    .navigationBarsPadding()
                    .windowInsetsTopHeight(WindowInsets(top = Spacing.huge_56)),
            )
        }
    }
}

@Composable
fun PagerTMDbItemContainer(
    feedWrapper: FeedWrapper,
    navController: NavController,
    onFeedClick: (TMDbItem) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { feedWrapper.feeds.size })

    Header(feedWrapper, navController)
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = Spacing.large_16),
    ) { page ->
        with(feedWrapper.feeds[page]) {
            TrendingItem(
                modifier =
                    Modifier.pagerTransition(
                        pagerState = pagerState,
                        page = page,
                    ),
                title = name,
                imageUrl = backdropUrl,
                releaseDate = releaseDate,
                onClick = { onFeedClick(this) },
            )
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainer
            Box(
                modifier =
                    Modifier.padding(Spacing.small_4)
                        .clip(CircleShape)
                        .background(color)
                        .size(Spacing.smallMedium_6),
            )
        }
    }
}

@Composable
fun TrendingItem(
    title: String,
    imageUrl: String?,
    releaseDate: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.d("IMAGE_DEBUG", "Trying to load: $imageUrl")
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(10.dp))
                .then(Modifier.clickable(onClick = onClick)),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier =
                    Modifier
                        .padding(
                            start = Spacing.mediumLarge_12,
                            bottom = Spacing.smallMedium_6,
                        )
                        .align(Alignment.BottomStart),
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(Spacing.smallMedium_6))
                releaseDate?.let { releaseDate ->
                    Text(
                        text = releaseDate,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun Header(feedWrapper: FeedWrapper, navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .heightIn(min = 36.dp)
                .padding(start = Spacing.mediumLarge_12),
    ) {
        Text(
            text = stringResource(id = feedWrapper.sortTypeResourceId),
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface,
            modifier =
                Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start),
        )
        Text(
            text = stringResource(R.string.more_item),
            color = MaterialTheme.colorScheme.onSurface,
            modifier =
                Modifier
                    .align(Alignment.CenterVertically)
                    .padding(Spacing.mediumLarge_12)
                    .clickable(
                        onClick = {
                            moreFeedOnClick(
                                feedWrapper.feeds.first(),
                                feedWrapper.sortType,
                                navController,
                            )
                        },
                    ),
        )
    }
}

@Composable
fun Feeds(
    feeds: List<TMDbItem>,
    onFeedClick: (TMDbItem) -> Unit,
    index: Int,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = Spacing.extraSmall_2, end = Spacing.extraSmall_2),
    ) {
        items(feeds) { feed ->
            if (index % 3 == 0) {
                TMDbCard(feed, onFeedClick, feed.backdropUrl, Spacing.giga_220)
            } else {
                TMDbCard(feed, onFeedClick, feed.posterUrl, Spacing.mega_120)
            }
        }
    }
}

private fun moreFeedOnClick(item: TMDbItem, sortType: SortType, navController: NavController) {
    when (item) {
        is Movie -> {
            when (sortType) {
                SortType.TRENDING -> navController.navigate(TMDB_TRENDING_MOVIES_ROUTE)
                SortType.MOST_POPULAR -> navController.navigate(TMDB_POPULAR_MOVIES_ROUTE)
                SortType.NOW_PLAYING -> navController.navigate(TMDB_NOW_PLAYING_MOVIES_ROUTE)
                SortType.UPCOMING -> navController.navigate(TMDB_UPCOMING_MOVIES_ROUTE)
                SortType.DISCOVER -> navController.navigate(TMDB_DISCOVER_MOVIES_ROUTE)
                SortType.HIGHEST_RATED ->
                    navController.navigate(
                        TMDB_TOP_RATED_MOVIES_ROUTE,
                    )
            }
        }

        is TVShow -> {
            when (sortType) {
                SortType.TRENDING -> navController.navigate(TMDB_TRENDING_TV_SHOW_ROUTE)
                SortType.MOST_POPULAR -> navController.navigate(TMDB_POPULAR_TV_SHOW_ROUTE)
                SortType.NOW_PLAYING -> navController.navigate(TMDB_AIRING_TODAY_TV_SHOW_ROUTE)
                SortType.UPCOMING -> navController.navigate(TMDB_ON_THE_AIR_TV_SHOW_ROUTE)
                SortType.DISCOVER -> navController.navigate(TMDB_DISCOVER_TV_SHOW_ROUTE)
                SortType.HIGHEST_RATED ->
                    navController.navigate(
                        TMDB_TOP_RATED_TV_SHOW_ROUTE,
                    )
            }
        }
    }
}

@Preview("default")
@Composable
fun FeedCardPreview() {
    PickItTheme {
        val movie = Movie(1, "", null, null, null, "Movie", 1.0, 2, listOf(28, 12, 16))
        Feeds(
            feeds = listOf(movie),
            onFeedClick = {},
            0,
        )
    }
}

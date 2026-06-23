package com.hitech.pickit.media.presentation.screens.paging.main

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.hitech.pickit.R
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.screens.paging.BasePagingViewModel
import com.hitech.pickit.media.presentation.screens.paging.PagingScreen
import com.hitech.pickit.media.presentation.screens.paging.main.movie.NowPlayingMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.PopularMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.SimilarMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.TopRatedMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.TrendingMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.UpcomingMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.AiringTodayTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.DiscoverTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.OnTheAirTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.PopularTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.SimilarTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.TopRatedTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.TrendingTvSeriesViewModel
import com.hitech.pickit.media.presentation.utili.components.DestinationBar
import com.hitech.pickit.navigation.MainDestinations.TMDB_MOVIE_DETAIL_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_SEARCH_MOVIE_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE

@Composable
fun TrendingMovieScreen(
    navController: NavController,
    viewModel: TrendingMoviesViewModel
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.trending,
            stringResource(R.string.movies),
        ),
    )
}

@Composable
fun PopularMovieScreen(
    navController: NavController,
    viewModel: PopularMoviesViewModel
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.popular,
            stringResource(R.string.movies),
        ),
    )
}

@Composable
fun NowPlayingMovieScreen(
    navController: NavController,
    viewModel: NowPlayingMoviesViewModel
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.now_playing,
            stringResource(R.string.movies),
        ),
    )
}

@Composable
fun UpcomingMovieScreen(
    navController: NavController,
    viewModel: UpcomingMoviesViewModel
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.upcoming,
            stringResource(R.string.movies),
        ),
    )
}

@Composable
fun TopRatedMovieScreen(
    navController: NavController,
    viewModel: TopRatedMoviesViewModel
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.highest_rate,
            stringResource(R.string.movies),
        ),
    )
}

@Composable
fun DiscoverMovieScreen(
    navController: NavController,
    viewModel: com.hitech.pickit.media.presentation.screens.BOOK_list.DiscoverMoviesViewModel
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.discover,
            stringResource(R.string.movies),
        ),
    )
}

@Composable
fun SimilarMovieScreen(
    navController: NavController,
    viewModel: SimilarMoviesViewModel
) {
    MoviePagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.similar_items,
            stringResource(R.string.movies),
        ),
    )
}

@Composable
fun TrendingTVShowScreen(
    navController: NavController,
    viewModel: TrendingTvSeriesViewModel
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.trending,
            stringResource(R.string.tv_series),
        ),
    )
}

@Composable
fun PopularTVShowScreen(
    navController: NavController,
    viewModel: PopularTvSeriesViewModel
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.popular,
            stringResource(R.string.tv_series),
        ),
    )
}

@Composable
fun AiringTodayTVShowScreen(
    navController: NavController,
    viewModel: AiringTodayTvSeriesViewModel
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.airing_today,
            stringResource(R.string.tv_series),
        ),
    )
}

@Composable
fun OnTheAirTVShowScreen(
    navController: NavController,
    viewModel: OnTheAirTvSeriesViewModel
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.on_the_air,
            stringResource(R.string.tv_series),
        ),
    )
}

@Composable
fun TopRatedTVShowScreen(
    navController: NavController,
    viewModel: TopRatedTvSeriesViewModel
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.highest_rate,
            stringResource(R.string.tv_series),
        ),
    )
}

@Composable
fun DiscoverTVShowScreen(
    navController: NavController,
    viewModel: DiscoverTvSeriesViewModel
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.discover,
            stringResource(R.string.tv_series),
        ),
    )
}

@Composable
fun SimilarTVShowScreen(
    navController: NavController,
    viewModel: SimilarTvSeriesViewModel
) {
    TVShowPagingScreen(
        viewModel = viewModel,
        navController = navController,
        title =
        stringResource(
            R.string.similar_items,
            stringResource(R.string.tv_series),
        ),
    )
}

@Composable
private fun MoviePagingScreen(
    viewModel: BasePagingViewModel<Movie>,
    navController: NavController,
    title: String
) {
    PagingScreen(
        viewModel = viewModel,
        navController = navController,
        onClick = { navController.navigate("${TMDB_MOVIE_DETAIL_ROUTE}/${it.id}") },
        onSearchClicked = { navController.navigate(TMDB_SEARCH_MOVIE_ROUTE) },
        title = title,
    )
}

@Composable
private fun TVShowPagingScreen(viewModel: BasePagingViewModel<TVShow>, navController: NavController, title: String) {
    PagingScreen(
        viewModel = viewModel,
        navController = navController,
        onClick = { navController.navigate("${TMDB_TV_SHOW_DETAIL_ROUTE}/${it.id}") },
        onSearchClicked = { navController.navigate(TMDB_SEARCH_TV_SHOW_ROUTE) },
        title = title,
    )
}

@Composable
private fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    navController: NavController,
    onClick: (TMDbItem) -> Unit,
    onSearchClicked: () -> Unit,
    title: String,
) {
    Box {
        PagingScreen(viewModel, onClick)
        DestinationBar(
            title = title,
            upPress = { navController.navigateUp() },
            onSearchClicked = onSearchClicked,
        )
    }
}

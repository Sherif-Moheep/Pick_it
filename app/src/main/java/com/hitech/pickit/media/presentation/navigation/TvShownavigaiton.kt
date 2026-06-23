package com.hitech.pickit.media.presentation.navigation

import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.hitech.pickit.media.presentation.screens.detail_screen.TVShowDetailScreen
import com.hitech.pickit.media.presentation.screens.detail_screen.TVShowDetailViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.AiringTodayTVShowScreen
import com.hitech.pickit.media.presentation.screens.paging.main.DiscoverTVShowScreen
import com.hitech.pickit.media.presentation.screens.paging.main.OnTheAirTVShowScreen
import com.hitech.pickit.media.presentation.screens.paging.main.PopularTVShowScreen
import com.hitech.pickit.media.presentation.screens.paging.main.SimilarTVShowScreen
import com.hitech.pickit.media.presentation.screens.paging.main.TopRatedTVShowScreen
import com.hitech.pickit.media.presentation.screens.paging.main.TrendingTVShowScreen
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.AiringTodayTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.DiscoverTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.OnTheAirTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.PopularTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.SimilarTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.TopRatedTvSeriesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.tvshow.TrendingTvSeriesViewModel
import com.hitech.pickit.navigation.MainDestinations.TMDB_AIRING_TODAY_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_DISCOVER_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_ID_KEY
import com.hitech.pickit.navigation.MainDestinations.TMDB_ON_THE_AIR_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_POPULAR_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_SIMILAR_ID
import com.hitech.pickit.navigation.MainDestinations.TMDB_SIMILAR_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TOP_RATED_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TRENDING_TV_SHOW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE

fun NavGraphBuilder.tvShowDetailGraph(navController: NavHostController) {
    val graphRoute = "tv_show_details_graph"

    navigation(
        startDestination = "${TMDB_TV_SHOW_DETAIL_ROUTE}/{${TMDB_ID_KEY}}",
        route = graphRoute
    ) {
        composable(
            route = "${TMDB_TV_SHOW_DETAIL_ROUTE}/{${TMDB_ID_KEY}}",
            arguments = listOf(navArgument(TMDB_ID_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: TVShowDetailViewModel = hiltViewModel(parentEntry)

            TVShowDetailScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

fun NavGraphBuilder.tvShowPagingScreens(navController: NavController) {

    composable(route = TMDB_TRENDING_TV_SHOW_ROUTE) {
        val viewModel: TrendingTvSeriesViewModel = hiltViewModel()
        TrendingTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_POPULAR_TV_SHOW_ROUTE) {
        val viewModel: PopularTvSeriesViewModel = hiltViewModel()
        PopularTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_AIRING_TODAY_TV_SHOW_ROUTE) {
        val viewModel: AiringTodayTvSeriesViewModel = hiltViewModel()
        AiringTodayTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_ON_THE_AIR_TV_SHOW_ROUTE) {
        val viewModel: OnTheAirTvSeriesViewModel = hiltViewModel()
        OnTheAirTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_TOP_RATED_TV_SHOW_ROUTE) {
        val viewModel: TopRatedTvSeriesViewModel = hiltViewModel()
        TopRatedTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_DISCOVER_TV_SHOW_ROUTE) {
        val viewModel: DiscoverTvSeriesViewModel = hiltViewModel()
        DiscoverTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(
        route = "${TMDB_SIMILAR_TV_SHOW_ROUTE}/{${TMDB_SIMILAR_ID}}",
        arguments = listOf(
            navArgument(TMDB_SIMILAR_ID) { type = NavType.IntType },
        ),
    ) {
        val viewModel: SimilarTvSeriesViewModel = hiltViewModel()
        SimilarTVShowScreen(navController = navController, viewModel = viewModel)
    }
}

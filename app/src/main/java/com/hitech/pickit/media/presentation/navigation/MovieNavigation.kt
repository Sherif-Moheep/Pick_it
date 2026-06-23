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
import com.hitech.pickit.media.presentation.screens.BOOK_list.DiscoverMoviesViewModel
import com.hitech.pickit.media.presentation.screens.credit.CastScreen
import com.hitech.pickit.media.presentation.screens.credit.CrewScreen
import com.hitech.pickit.media.presentation.screens.detail_screen.MovieDetailScreen
import com.hitech.pickit.media.presentation.screens.detail_screen.MovieDetailViewModel
import com.hitech.pickit.media.presentation.screens.image.ImagesScreen
import com.hitech.pickit.media.presentation.screens.paging.main.DiscoverMovieScreen
import com.hitech.pickit.media.presentation.screens.paging.main.NowPlayingMovieScreen
import com.hitech.pickit.media.presentation.screens.paging.main.PopularMovieScreen
import com.hitech.pickit.media.presentation.screens.paging.main.SimilarMovieScreen
import com.hitech.pickit.media.presentation.screens.paging.main.TopRatedMovieScreen
import com.hitech.pickit.media.presentation.screens.paging.main.TrendingMovieScreen
import com.hitech.pickit.media.presentation.screens.paging.main.UpcomingMovieScreen
import com.hitech.pickit.media.presentation.screens.paging.main.movie.NowPlayingMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.PopularMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.SimilarMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.TopRatedMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.TrendingMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.main.movie.UpcomingMoviesViewModel
import com.hitech.pickit.navigation.MainDestinations.TMDB_CAST_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_CREW_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_DISCOVER_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_ID_KEY
import com.hitech.pickit.navigation.MainDestinations.TMDB_IMAGES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_MOVIE_DETAIL_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_NOW_PLAYING_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_POPULAR_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_SIMILAR_ID
import com.hitech.pickit.navigation.MainDestinations.TMDB_SIMILAR_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TOP_RATED_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_TRENDING_MOVIES_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_UPCOMING_MOVIES_ROUTE

fun NavGraphBuilder.movieDetailGraph(navController: NavHostController) {
    val graphRoute = "movie_details_graph"

    navigation(
        startDestination = "${TMDB_MOVIE_DETAIL_ROUTE}/{${TMDB_ID_KEY}}",
        route = graphRoute
    ) {

        composable(
            route = "${TMDB_MOVIE_DETAIL_ROUTE}/{${TMDB_ID_KEY}}",
            arguments = listOf(navArgument(TMDB_ID_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: MovieDetailViewModel = hiltViewModel(parentEntry)

            MovieDetailScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = "${TMDB_IMAGES_ROUTE}/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: MovieDetailViewModel = hiltViewModel(parentEntry)
            val index = backStackEntry.arguments?.getInt("index") ?: 0

            ImagesScreen(
                viewModel = viewModel,
                initialPage = index,
                onUpPress = { navController.navigateUp() }
            )
        }

        composable(route = TMDB_CAST_ROUTE) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: MovieDetailViewModel = hiltViewModel(parentEntry)

            CastScreen(
                viewModel = viewModel,
                onUpPress = { navController.navigateUp() }
            )
        }

        composable(route = TMDB_CREW_ROUTE) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: MovieDetailViewModel = hiltViewModel(parentEntry)

            CrewScreen(
                viewModel = viewModel,
                onUpPress = { navController.navigateUp() }
            )
        }
    }
}


fun NavGraphBuilder.moviePagingScreens(navController: NavController) {

    composable(route = TMDB_TRENDING_MOVIES_ROUTE) {
        val viewModel: TrendingMoviesViewModel = hiltViewModel()
        TrendingMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_POPULAR_MOVIES_ROUTE) {
        val viewModel: PopularMoviesViewModel = hiltViewModel()
        PopularMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_NOW_PLAYING_MOVIES_ROUTE) {
        val viewModel: NowPlayingMoviesViewModel = hiltViewModel()
        NowPlayingMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_UPCOMING_MOVIES_ROUTE) {
        val viewModel: UpcomingMoviesViewModel = hiltViewModel()
        UpcomingMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_TOP_RATED_MOVIES_ROUTE) {
        val viewModel: TopRatedMoviesViewModel = hiltViewModel()
        TopRatedMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = TMDB_DISCOVER_MOVIES_ROUTE) {
        val viewModel: DiscoverMoviesViewModel = hiltViewModel()
        DiscoverMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(
        route = "${TMDB_SIMILAR_MOVIES_ROUTE}/{${TMDB_SIMILAR_ID}}",
        arguments = listOf(
            navArgument(TMDB_SIMILAR_ID) { type = NavType.IntType },
        ),
    ) {
        val viewModel: SimilarMoviesViewModel = hiltViewModel()
        SimilarMovieScreen(navController = navController, viewModel = viewModel)
    }
}


package com.hitech.pickit.media.presentation.navigation

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hitech.pickit.media.presentation.screens.paging.search.SearchMoviesScreen
import com.hitech.pickit.media.presentation.screens.paging.search.SearchMoviesViewModel
import com.hitech.pickit.media.presentation.screens.paging.search.SearchTVSeriesScreen
import com.hitech.pickit.media.presentation.screens.paging.search.SearchTVSeriesViewModel
import com.hitech.pickit.navigation.MainDestinations.TMDB_SEARCH_MOVIE_ROUTE
import com.hitech.pickit.navigation.MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE

fun NavGraphBuilder.searchScreens(navController: NavController) {

    composable(route = TMDB_SEARCH_MOVIE_ROUTE) {
        val viewModel: SearchMoviesViewModel = hiltViewModel()
        SearchMoviesScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable(route = TMDB_SEARCH_TV_SHOW_ROUTE) {
        val viewModel: SearchTVSeriesViewModel = hiltViewModel()
        SearchTVSeriesScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}

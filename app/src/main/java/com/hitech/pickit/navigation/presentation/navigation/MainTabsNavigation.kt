package com.hitech.pickit.navigation.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hitech.pickit.R
import com.hitech.pickit.media.presentation.screens.BOOK_list.BookmarkViewModel
import com.hitech.pickit.media.presentation.screens.BOOK_list.UnifiedFavListScreen
import com.hitech.pickit.media.presentation.screens.feed.MovieFeedScreen
import com.hitech.pickit.media.presentation.screens.feed.MovieFeedViewModel
import com.hitech.pickit.media.presentation.screens.feed.TVShowFeedScreen
import com.hitech.pickit.media.presentation.screens.feed.TVShowFeedViewModel
import com.hitech.pickit.media.presentation.screens.noDataEmptyScreen.EmptyScreen
import com.hitech.pickit.navigation.MainDestinations
import com.hitech.pickit.navigation.MainDestinations.HOME_ROUTE
import com.hitech.pickit.navigation.MainDestinations.SIGNIN_ROUTE
import com.hitech.pickit.navigation.BottomNavItem
import com.hitech.pickit.profile.presentation.ProfileScreen

fun NavGraphBuilder.navigationScreens(navController: NavController) {
    navigation(
        route = HOME_ROUTE,
        startDestination = BottomNavItem.MOVIE_SECTION.route,
    ) {
        composable(route = BottomNavItem.MOVIE_SECTION.route) {
            val viewModel: MovieFeedViewModel = hiltViewModel()
            MovieFeedScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = BottomNavItem.TV_SHOW_SECTION.route) {
            val viewModel: TVShowFeedViewModel = hiltViewModel()
            TVShowFeedScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = BottomNavItem.BOOKMARK_SECTION.route
        ) {
            val viewModel: BookmarkViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()

            LaunchedEffect(Unit) { viewModel.loadBookmarks() }

            var selectedTab by rememberSaveable { mutableIntStateOf(0) }
            val tabs = listOf("Movies", "TV Shows")

            Column {
                TabRow(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = 16.dp),
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }

                when (selectedTab) {
                    0 -> {
                        if (state.movies.isNotEmpty()) {
                            UnifiedFavListScreen(
                                items = state.movies,
                                isLoading = state.isLoading,
                                onItemClick = { id ->
                                    navController.navigate("${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/$id")
                                }
                            )
                        } else {
                            EmptyScreen(
                                message = stringResource(R.string.no_favorite_movies)
                            )
                        }
                    }

                    1 -> {
                        if (state.tvShows.isNotEmpty()) {
                            UnifiedFavListScreen(
                                items = state.tvShows,
                                isLoading = state.isLoading,
                                onItemClick = { id ->
                                    navController.navigate("${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/$id")
                                }
                            )
                        } else {
                            EmptyScreen(

                                message = stringResource(R.string.no_favorite_tv_shows)
                            )
                        }
                    }
                }
            }
        }


        composable(route = BottomNavItem.SETTING_SECTION.route) {
            ProfileScreen(
                onSignActionClick = {
                    navController.navigate(SIGNIN_ROUTE) {
                        popUpTo(HOME_ROUTE) { inclusive = true }
                    }
                }
            )
        }
    }
}

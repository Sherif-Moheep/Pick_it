package com.hitech.pickit.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.hitech.pickit.navigation.presentation.components.TMDbBottomBar
import com.hitech.pickit.core.presentation.MainViewModel

@Composable
fun PickItApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val appState = rememberPickItAppState()
    val isLoading by viewModel.isLoading.collectAsState()
    val startDestination by viewModel.startDestination.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                TMDbBottomBar(
                    tabs = appState.bottomBarTabs,
                    currentRoute = appState.currentRoute ?: BottomNavItem.MOVIE_SECTION.route,
                    navigateToRoute = { route -> appState.navigateToBottomBarRoute(route) },
                )
            }
        },
    ) { innerPaddingModifier ->
        PickItNavGraph(
            navController = appState.navController,
            startDestination = startDestination,
            innerPadding = innerPaddingModifier,
            viewModel = viewModel
        )
//        val newPadding =
//            PaddingValues(
//                start = innerPaddingModifier.calculateStartPadding(LocalLayoutDirection.current),
//                end = innerPaddingModifier.calculateEndPadding(LocalLayoutDirection.current),
//                bottom = innerPaddingModifier.calculateBottomPadding(),
//                top = 0.dp
//            )
//        NavHost(
//            navController = appState.navController,
//            startDestination = startDestination,
//            modifier = Modifier.padding(newPadding),
//        ) {
//            onboardingScreen(appState.navController, viewModel)
//            navigationScreens(appState.navController)
//
//            movieDetailGraph(appState.navController)
//
//            tvShowDetailGraph(appState.navController)
//
//            searchScreens(appState.navController)
//
//            personScreen(appState.navController)
//
//            moviePagingScreens(appState.navController)
//            tvShowPagingScreens(appState.navController)
//
//            signInScreen(navController = appState.navController)
//        }
//    }


    }
}
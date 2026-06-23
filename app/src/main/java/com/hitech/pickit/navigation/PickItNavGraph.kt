package com.hitech.pickit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hitech.pickit.auth.presentation.navigation.signInScreen
import com.hitech.pickit.media.presentation.navigation.movieDetailGraph
import com.hitech.pickit.media.presentation.navigation.moviePagingScreens
import com.hitech.pickit.media.presentation.navigation.personScreen
import com.hitech.pickit.media.presentation.navigation.searchScreens
import com.hitech.pickit.media.presentation.navigation.tvShowDetailGraph
import com.hitech.pickit.media.presentation.navigation.tvShowPagingScreens
import com.hitech.pickit.navigation.presentation.navigation.navigationScreens
import com.hitech.pickit.onboarding.presentation.navigation.onboardingScreen
import com.hitech.pickit.core.presentation.MainViewModel

@Composable
fun PickItNavGraph(
    navController: NavHostController,
    startDestination: String,
    innerPadding: PaddingValues,
    viewModel: MainViewModel
) {
    val layoutDirection = LocalLayoutDirection.current
    val newPadding = PaddingValues(
        start = innerPadding.calculateStartPadding(layoutDirection),
        end = innerPadding.calculateEndPadding(layoutDirection),
        bottom = innerPadding.calculateBottomPadding(),
        top = 0.dp
    )

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(newPadding),
    ) {
        onboardingScreen(navController, viewModel)
        navigationScreens(navController)

        movieDetailGraph(navController)
        tvShowDetailGraph(navController)
        searchScreens(navController)
        personScreen(navController)

        moviePagingScreens(navController)
        tvShowPagingScreens(navController)

        signInScreen(navController = navController)
    }
}

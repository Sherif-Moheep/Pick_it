package com.hitech.pickit.media.presentation.navigation

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hitech.pickit.media.presentation.screens.credit.PersonScreen
import com.hitech.pickit.media.presentation.screens.credit.PersonViewModel
import com.hitech.pickit.navigation.MainDestinations.TMDB_PERSON_KEY
import com.hitech.pickit.navigation.MainDestinations.TMDB_PERSON_ROUTE

fun NavGraphBuilder.personScreen(navController: NavController) {
    composable(
        route = "${TMDB_PERSON_ROUTE}/{${TMDB_PERSON_KEY}}",
        arguments = listOf(
            navArgument(TMDB_PERSON_KEY) { type = NavType.StringType },
        ),
    ) {
        val viewModel: PersonViewModel = hiltViewModel()

        PersonScreen(
            upPress = { navController.navigateUp() },
            viewModel = viewModel
        )
    }
}
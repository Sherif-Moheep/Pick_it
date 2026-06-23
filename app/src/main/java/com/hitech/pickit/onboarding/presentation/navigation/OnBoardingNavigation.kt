package com.hitech.pickit.onboarding.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hitech.pickit.navigation.MainDestinations.HOME_ROUTE
import com.hitech.pickit.onboarding.presentation.MainOnboardingFlow
import com.hitech.pickit.core.presentation.MainViewModel
import com.hitech.pickit.navigation.MainDestinations.ONBOARDING_ROUTE

fun NavGraphBuilder.onboardingScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    composable(route = ONBOARDING_ROUTE) {
        MainOnboardingFlow(
            onFinish = {
                viewModel.saveOnBoardingState(completed = true)

                navController.navigate(HOME_ROUTE) {
                    popUpTo(ONBOARDING_ROUTE) { inclusive = true }
                }
            }
        )
    }
}
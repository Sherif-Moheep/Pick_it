package com.hitech.pickit.auth.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hitech.pickit.auth.presentation.SignInScreen
import com.hitech.pickit.auth.presentation.SignInViewModel
import com.hitech.pickit.navigation.MainDestinations.HOME_ROUTE
import com.hitech.pickit.navigation.MainDestinations.SIGNIN_ROUTE

fun NavGraphBuilder.signInScreen(
    navController: NavController,
) {
    composable(route = SIGNIN_ROUTE) {
        val viewModel = hiltViewModel<SignInViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val context = LocalContext.current

        LaunchedEffect(key1 = state.isSignInSuccessful) {
            if (state.isSignInSuccessful) {
                navController.navigate(HOME_ROUTE) {
                    popUpTo(SIGNIN_ROUTE) { inclusive = true }
                }
            }
        }

        SignInScreen(
            state = state,
            onGoogleSignInClick = {
                viewModel.signInWithGoogle(context)
            },
            onGuestClick = {
                navController.navigate(HOME_ROUTE) {
                    popUpTo(SIGNIN_ROUTE) { inclusive = true }
                }
            }
        )
    }
}
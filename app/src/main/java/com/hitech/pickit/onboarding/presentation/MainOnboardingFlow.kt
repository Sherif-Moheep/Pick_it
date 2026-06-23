package com.hitech.pickit.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hitech.pickit.onboarding.presentation.components.FirstOnBoardingScreen
import com.hitech.pickit.onboarding.presentation.components.OnboardingScreen
import com.hitech.pickit.onboarding.presentation.components.SecondOnBoardingScreen
import com.hitech.pickit.onboarding.presentation.components.ThirdOnBoardingScreen
import com.hitech.pickit.core.presentation.theme.PickItTheme

@Composable
fun MainOnboardingFlow(onFinish: () -> Unit) {

    val totalPages = 3

    OnboardingScreen(
        pageCount = totalPages,
        onFinish = onFinish,
        pageContent = { pageIndex ->

            when (pageIndex) {
                0 -> FirstOnBoardingScreen()
                1 -> SecondOnBoardingScreen()
                2 -> ThirdOnBoardingScreen()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainOnboardingFlowPreview() {
    PickItTheme {
        MainOnboardingFlow(onFinish = {})
    }
}
package com.hitech.pickit.onboarding.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    pageCount: Int,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    pageContent: @Composable (page: Int) -> Unit
) {
    val pagerState = rememberPagerState { pageCount }
    val scope = rememberCoroutineScope()
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(pagerState.currentPage) {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            pageContent(page)
        }
    }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

            OnboardingControls(
                pagerState = pagerState,
                scope = scope,
                pageCount = pageCount,
                onFinish = onFinish
            )
        }
}


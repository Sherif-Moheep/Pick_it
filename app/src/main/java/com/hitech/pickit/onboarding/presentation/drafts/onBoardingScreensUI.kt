//package com.hitech.pickit.feature_onboarding.presentation.drafts
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.hitech.pickit.R
//import kotlinx.coroutines.launch
//
//
//@Composable
//fun OnboardingScreen(onFinish: () -> Unit) {
//    val pages = listOf(
//        OnBoardingPage("Welcome", "Welcome to PickIt!", R.drawable.ic_launcher_foreground),
//        OnBoardingPage("Features", "Discover awesome features." ,R.drawable.ic_launcher_foreground),
//        OnBoardingPage("Get Started", "Let's Pick it !" ,R.drawable.ic_launcher_foreground)
//    )
//
//    val pagerState = rememberPagerState { pages.size }
//    val scope = rememberCoroutineScope()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        HorizontalPager(
//            state = pagerState,
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//        ) { page ->
//            OnboardingPageContent(pageData = pages[page])
//        }
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            DotsIndicator(totalDots = pages.size, selectedIndex = pagerState.currentPage)
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                TextButton(onClick = onFinish) { Text("Skip") }
//
//                if (pagerState.currentPage == pages.lastIndex) {
//                    Button(onClick = onFinish) { Text("Let's pick it") }
//                } else {
//                    Button(onClick = {
//                        scope.launch {
//                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                        }
//                    }) {
//                        Text("Next")
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun OnboardingPageContent(pageData: OnBoardingPage) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 60.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = pageData.title,
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//        Text(
//            text = pageData.description,
//            style = MaterialTheme.typography.bodyLarge
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//        Text(
//            text = pageData.description,
//            style = MaterialTheme.typography.bodyLarge
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//        Image(
//            painter = painterResource(id = pageData.imageRes),
//            contentDescription = null,
//            modifier = Modifier.size(200.dp)
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//        Text(
//            text = pageData.description,
//            style = MaterialTheme.typography.bodyLarge
//        )
//
//    }
//}
//
//
//
//@Composable
//fun DotsIndicator(totalDots: Int, selectedIndex: Int) {
//    Row(
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        repeat(totalDots) { index ->
//            val color =
//                if (index == selectedIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
//                    alpha = 0.3f
//                )
//            Box(
//                modifier = Modifier
//                    .padding(4.dp)
//                    .size(if (index == selectedIndex) 12.dp else 8.dp)
//                    .background(color, shape = MaterialTheme.shapes.small)
//            )
//        }
//    }
//}
//
//
//@Preview
//@Composable
//fun OnboardingScreenPreview() {
//    OnboardingScreen(onFinish = {})
//}
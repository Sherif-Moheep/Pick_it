package com.hitech.pickit

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hitech.pickit.profile.presentation.util.AppTheme
import com.hitech.pickit.profile.presentation.viewmodel.ProfileViewModel
import com.hitech.pickit.navigation.PickItApp
import com.hitech.pickit.core.presentation.theme.PickItTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PickItTheme {
                MyApp()

//                MainOnboardingFlow(onFinish = {})

//                Scaffold(
//                    Modifier.fillMaxSize(),
//                    containerColor = MaterialTheme.colorScheme.surface
//                ) { innerPadding ->
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding), contentAlignment = Alignment.Center
//                    ) {
//
//                    }
//                }

            }
        }
    }
}

@Composable
fun MyApp() {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val appTheme by profileViewModel.theme.collectAsStateWithLifecycle()

    val isDark = appTheme == AppTheme.DARK

    var showSplash by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(2000)
        showSplash = false
    }

    PickItTheme(darkTheme = isDark) {
        if (showSplash) {
            // ممكن تحط هنا شاشة Splash مخصصة
            // SplashScreen()
        } else {
            PickItApp()
        }
    }
}
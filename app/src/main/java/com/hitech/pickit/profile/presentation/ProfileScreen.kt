package com.hitech.pickit.profile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hitech.pickit.R
import com.hitech.pickit.profile.presentation.components.LanguageDialog
import com.hitech.pickit.profile.presentation.components.LanguageProfileMenuItem
import com.hitech.pickit.profile.presentation.components.ProfileHeader
import com.hitech.pickit.profile.presentation.components.ProfileScreenGradient
import com.hitech.pickit.profile.presentation.components.ProfileTopAppBar
import com.hitech.pickit.profile.presentation.components.SignActionButton
import com.hitech.pickit.profile.presentation.components.ThemeMenuItem
import com.hitech.pickit.profile.presentation.components.ToggleMenuItem
import com.hitech.pickit.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    onSignActionClick: () -> Unit
) {
    val context = LocalContext.current

    val user by viewModel.user.collectAsStateWithLifecycle()

    val currentTheme by viewModel.theme.collectAsStateWithLifecycle()
    val currentLanguage by viewModel.currentLanguage.collectAsStateWithLifecycle()

    var showLanguageDialog by remember { mutableStateOf(false) }

    var isNotificationChecked by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        ProfileScreenGradient(
            imageUrl = user?.profilePictureUrl
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                ProfileTopAppBar()
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileHeader(
                        userData = user
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ToggleMenuItem(
                        icon = R.drawable.notifications_icon,
                        text = stringResource(R.string.Notifications),
                        isChecked = isNotificationChecked,
                        onCheckedChange = { isNotificationChecked = !isNotificationChecked }
                    )

                    LanguageProfileMenuItem(
                        icon = R.drawable.language_icon,
                        text = stringResource(R.string.Language),
                        selectedLanguage = currentLanguage.displayName,
                        onClick = { showLanguageDialog = true }
                    )

                    ThemeMenuItem(
                        currentTheme = currentTheme,
                        onThemeChange = { viewModel.setTheme(it) } ,
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 5.dp),
                    )

                    if (user != null) {
                        // user is logged in with an account
                        SignActionButton(
                            icon = Icons.AutoMirrored.Filled.Logout,
                            text = stringResource(R.string.sign_out),
                            isDestructive = true,
                            onClick = {
                                viewModel.signOut(context)
                                // Navigate to the sing in screen
                                onSignActionClick()
                            }
                        )
                    } else {
                        // user is in guest mode
                        SignActionButton(
                            icon = Icons.AutoMirrored.Filled.Login,
                            text = stringResource(R.string.sign_in),
                            isDestructive = false,
                            onClick = {
                                // Navigate to the sing in screen
                                onSignActionClick()
                            }
                        )
                    }
                }
            }
        )

        LanguageDialog(
            openDialog = showLanguageDialog,
            currentAppLanguage = currentLanguage,
            onLanguageSelected = { selectedLanguage ->
                viewModel.setLanguage(selectedLanguage)
                showLanguageDialog = false
            },
            onDismiss = { showLanguageDialog = false }
        )
    }
}
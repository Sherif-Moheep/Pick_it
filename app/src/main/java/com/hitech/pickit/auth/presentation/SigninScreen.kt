@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.hitech.pickit.auth.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.core.presentation.theme.PickItTheme

@Composable
fun SignInScreen(
    state: SignInState,
    onGoogleSignInClick: () -> Unit,
    onGuestClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // --- LAYER 1: Background Image ---
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(15.dp)
        )

        // --- LAYER 2: Dark Overlay ---
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
//                            Color.Black.copy(alpha = 0.3f),
//                            Color.Black.copy(alpha = 0.8f),
//                            Color.Black

                            MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
        )

        // --- LAYER 3: Content ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // App Logo
            Image(
                painter = painterResource(id = R.drawable.pickit_logo_big),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // App Name
            Text(
                text = "Pick It",
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            // 3. Tagline / Value Prop
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your personal movie guide.\nTrack, rate, and discover.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Google Sign In Button
            Button(
                onClick = onGoogleSignInClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                enabled = !state.isLoading // Disable button while loading
            ) {
                if (state.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoadingIndicator(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(250.dp)
                        )
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Continue with Google",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Guest Mode Button
            OutlinedButton(
                onClick = onGuestClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.inverseSurface
                )
            ) {
                Text(
                    text = "Continue as Guest",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer / Legal Text
            val legalText = buildAnnotatedString {
                append("By continuing, you agree to our ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Terms")
                }
                append(" & ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Privacy Policy")
                }
                append(".")
            }

            Text(
                text = legalText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun SignInScreenPreview() {
    PickItTheme {
        SignInScreen(
            state = SignInState(
                isSignInSuccessful = false,
                signInError = null,
                isLoading = false
            ),
            onGoogleSignInClick = {},
            onGuestClick = {}
        )
    }
}
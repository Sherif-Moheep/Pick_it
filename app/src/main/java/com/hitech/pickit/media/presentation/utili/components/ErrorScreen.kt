package com.hitech.pickit.media.presentation.utili.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.hitech.pickit.R
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.utili.Spacing


@Composable
fun ErrorScreen(
    message: String,
    modifier: Modifier = Modifier,
    refresh: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            style =
            MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
            ),
        )
        Spacer(modifier = Modifier.height(Spacing.large_16))
        Button(
            onClick = refresh,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = Color.White),
            shape = RoundedCornerShape(Spacing.large_16)
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@PreviewLightDark
@Composable
private fun Test() {
    PickItTheme {
        ErrorScreen(message = "Error", refresh = {})
    }
}

package com.hitech.pickit.profile.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.profile.presentation.util.AppTheme
import com.hitech.pickit.core.presentation.theme.PickItTheme

@Composable
fun ThemeMenuItem(
    currentTheme: AppTheme,
    onThemeChange: (AppTheme) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        ThemeToggleButton(
            text = stringResource(R.string.dark_mode),
            icon = R.drawable.dark_mode_icon,
            isSelected = currentTheme == AppTheme.DARK,
            onClick = { onThemeChange(AppTheme.DARK) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))

        ThemeToggleButton(
            text = stringResource(R.string.light_mode),
            icon = R.drawable.light_mode_icon,
            isSelected = currentTheme == AppTheme.LIGHT,
            onClick = { onThemeChange(AppTheme.LIGHT) },
            modifier = Modifier.weight(1f)
        )
    }
}
@Composable
private fun ThemeToggleButton(
    text: String,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        },
        animationSpec = tween(durationMillis = 300),
    )

    val contentColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(durationMillis = 300),
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = text,
                tint = contentColor,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = contentColor,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ThemeSwitcherPreview() {
    PickItTheme {
        var currentTheme by remember { mutableStateOf(AppTheme.DARK) }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ThemeMenuItem(
                currentTheme = currentTheme,
                onThemeChange = {
                    currentTheme = it
                }
            )
        }
    }
}
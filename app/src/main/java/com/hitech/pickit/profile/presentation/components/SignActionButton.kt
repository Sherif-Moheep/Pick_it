package com.hitech.pickit.profile.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.hitech.pickit.core.presentation.theme.PickItTheme

@Composable
fun SignActionButton(
    icon: ImageVector,
    text: String,
    isDestructive: Boolean = false,
    onClick: () -> Unit
) {
    val color =
        if (isDestructive) MaterialTheme.colorScheme.error
        else MaterialTheme.colorScheme.primary
    val containerColor =
        if (isDestructive) MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f)
        else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)

    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        color = containerColor
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SignActionButtonPreview() {
    PickItTheme {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // sign in
            SignActionButton(
                icon = Icons.AutoMirrored.Filled.Login,
                text = "Sign In",
                isDestructive = false,
                onClick = {}
            )

            // sign out
            SignActionButton(
                icon = Icons.AutoMirrored.Filled.Logout,
                text = "Sign Out",
                isDestructive = true,
                onClick = {}
            )
        }
    }
}
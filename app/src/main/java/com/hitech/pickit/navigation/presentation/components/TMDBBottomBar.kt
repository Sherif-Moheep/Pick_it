package com.hitech.pickit.navigation.presentation.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hitech.pickit.navigation.BottomNavItem

@Composable
fun TMDbBottomBar(
    tabs: Array<BottomNavItem>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier.navigationBarsPadding()
    ) {
        tabs.forEach { section ->
            val selected = section.route == currentRoute

            NavigationBarItem(
                selected = selected,
                onClick = { navigateToRoute(section.route) },

                icon = {
                    Icon(
                        imageVector = if (selected) section.selectedIcon else section.unselectedIcon,
                        contentDescription = stringResource(id = section.title)
                    )
                },

                label = {
                    Text(
                        text = stringResource(id = section.title),
                        style = MaterialTheme.typography.labelMedium
                    )
                },

                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,

                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,

                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
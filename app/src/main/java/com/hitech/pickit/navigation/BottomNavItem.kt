package com.hitech.pickit.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.hitech.pickit.R

enum class BottomNavItem(
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
) {
    MOVIE_SECTION(
        route = "Movie",
        title = R.string.movie,
        unselectedIcon = Icons.Outlined.Movie,
        selectedIcon = Icons.Filled.Movie
    ),

    TV_SHOW_SECTION(
        route = "TVShow",
        title = R.string.tv_show,
        unselectedIcon = Icons.Outlined.Tv,
        selectedIcon = Icons.Filled.Tv
    ),

    BOOKMARK_SECTION(
        route = "Bookmark",
        title = R.string.favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite
    ),

    SETTING_SECTION(
        route = "Setting",
        title = R.string.setting,
        unselectedIcon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings
    ),
}
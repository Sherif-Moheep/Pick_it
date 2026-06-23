package com.hitech.pickit.media.presentation.screens.seachScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hitech.pickit.media.presentation.screens.seachScreen.components.MoviePoster
import com.hitech.pickit.media.presentation.screens.seachScreen.components.SearchBar
import com.hitech.pickit.media.presentation.screens.seachScreen.components.SearchData
import com.hitech.pickit.core.presentation.theme.PickItTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }, containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SearchBar(searchText = searchText, onTextChange = { searchText = it })
            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle("Recent searches")
            RecentSearchesRow(
                recentSearches = SearchData.recentSearches, onItemClick = { searchText = it })
            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle("Genres")
            TagsGrid(
                tags = SearchData.genres, onTagClick = { searchText = it })
            ViewMoreButton(label = "View more", onClick = { /* TODO: Genres list */ })
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("Languages")
            TagsGrid(
                tags = SearchData.languages, onTagClick = { searchText = it })
            ViewMoreButton(label = "View more", onClick = { /* TODO: Languages list */ })
        }
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun RecentSearchesRow(recentSearches: List<MoviePoster>, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        recentSearches.forEach { movie ->
            MoviePosterCard(movie) { selectedText ->
                onItemClick(selectedText)
            }
        }
    }
}

@Composable
fun MoviePosterCard(movie: MoviePoster, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick(movie.title) }) {
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
        )
        Text(
            text = movie.title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsGrid(tags: List<String>, onTagClick: (String) -> Unit) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            FilterChip(
                selected = false,
                onClick = { onTagClick(tag) },
                label = {
                    Text(
                        tag,
                        color =Color.White
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surfaceBright,
                    labelColor =Color.White,
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedLabelColor = Color.White
                ),
                border = null
            )
        }
    }
}

@Composable
fun ViewMoreButton(label: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.primaryContainer,
            fontSize = 14.sp
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White
                )
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor =MaterialTheme.colorScheme.primaryContainer)
        )
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primaryContainer
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onSurface,
                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                indicatorColor = MaterialTheme.colorScheme.surface
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primaryContainer)
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewSearchScreen() {
    PickItTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            SearchScreen()
        }
    }
}
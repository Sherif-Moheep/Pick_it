//package com.hitech.pickit.feature_content.presentation.screens.seachScreen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.PreviewLightDark
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.hitech.pickit.R
//import com.hitech.pickit.feature_content.presentation.screens.seachScreen.components.DeclarativeItems
//import com.hitech.pickit.feature_content.presentation.screens.seachScreen.components.RecentSearches
//import com.hitech.pickit.feature_content.presentation.screens.seachScreen.components.SearchBar
//import com.hitech.pickit.feature_content.presentation.screens.seachScreen.components.SearchData
//import com.hitech.pickit.ui.theme.PickItTheme
//
//@Composable
//fun SearchScreen(modifier: Modifier = Modifier) {
//    var searchText by remember { mutableStateOf("") }
//
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.surface)
//            .padding(16.dp)
//    ) {
//        SearchBar(searchText = searchText, onTextChange = { searchText = it })
//
//        Spacer(Modifier.height(20.dp))
//
//        RecentSearches(
//            recentSearches = SearchData.recentSearches,
//            onItemClick = { searchText = it }
//        )
//        Spacer(Modifier.height(20.dp))
//
//        DeclarativeItems(
//            title = R.string.genres,
//            tags = SearchData.genres,
//            onTagClick = { searchText = it }
//        )
//        TextButton(onClick = {}) {
//            Text(
//                text = stringResource(R.string.view_more),
//                color = MaterialTheme.colorScheme.primaryContainer,
//                fontSize = 14.sp
//            )
//            Icon(
//                imageVector = Icons.Default.KeyboardArrowDown,
//                contentDescription = stringResource(R.string.view_more),
//                tint = MaterialTheme.colorScheme.primaryContainer,
//                modifier = Modifier.size(20.dp)
//            )
//        }
//
//        Spacer(Modifier.height(20.dp))
//
//        DeclarativeItems(
//            title = R.string.languages,
//            tags = SearchData.languages,
//            onTagClick = { searchText = it }
//        )
//
//
//    }
//
//
//}
//
//@PreviewLightDark
//@Composable
//private fun PreviewSearchScreen() {
//    PickItTheme {
//        SearchScreen()
//    }
//}
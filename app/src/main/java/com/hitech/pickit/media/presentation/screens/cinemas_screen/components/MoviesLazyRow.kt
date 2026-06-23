@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.hitech.pickit.media.presentation.screens.cinemas_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.media.presentation.screens.BOOK_list.components.MoviePreview
import com.hitech.pickit.media.presentation.util.MovieState

@Composable
fun MoviesLazyRow(
    modifier: Modifier = Modifier,
    state: MovieState,
) {
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            //shimmer
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                LoadingIndicator(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(250.dp)
                )
            }
        }
    } else {

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            items(state.movies) { movie ->

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 35.dp, end = 50.dp)
                        .padding(start = 20.dp)
                ) {
                    PostBorder {
                        Image(
                            //AsyncImage()
                            // model = movie.picture,
                            painter = painterResource(R.drawable.the_dark_knight_poster),
                            contentDescription = "",
                            modifier = modifier
                                .width(250.dp)
                                .height(375.dp)
                        )
                    }
                }
            }

        }


    }

}

@Preview
@Composable
private fun MoviesLazyRowPreview() {
    MoviesLazyRow(
        state = MovieState(
            isLoading = false,
            movies = (1..10).map { MoviePreview.copy(id = it) },
        )
    )
}


package com.hitech.pickit.profile.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.hitech.pickit.R
import com.hitech.pickit.core.presentation.theme.PickItTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.profile),
                color = MaterialTheme.colorScheme.primaryContainer,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            Color.Transparent
        )
    )
}
@Preview()
@Composable
private fun ProfileTopAppbarPreview() {
    PickItTheme {
        ProfileTopAppBar()
    }
}
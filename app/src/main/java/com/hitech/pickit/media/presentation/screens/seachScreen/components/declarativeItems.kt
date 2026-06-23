package com.hitech.pickit.media.presentation.screens.seachScreen.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun DeclarativeItems(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    tags: List<String>,
    onTagClick: (String) -> Unit

) {
    var selectedTag by remember { mutableStateOf<String?>(null) }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(title),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )


        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tags.forEach { tag ->
                FilterChip(
                    selected = tag == selectedTag, onClick = {
                        selectedTag = tag
                        onTagClick(tag)
                    }, label = {
                        Text(
                            tag,
                            color = if (tag == selectedTag) MaterialTheme.colorScheme.onPrimaryContainer
                            else MaterialTheme.colorScheme.onSurface
                        )
                    }, colors = FilterChipDefaults.filterChipColors(
                        containerColor = if (tag == selectedTag) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.surfaceVariant,
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        labelColor = MaterialTheme.colorScheme.onSurface
                    ), border = null, modifier = Modifier.heightIn(min = 35.dp, max = 45.dp)
                )
            }
        }
    }
}

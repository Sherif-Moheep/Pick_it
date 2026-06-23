package com.hitech.pickit.media.presentation.screens.cinemas_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.core.presentation.theme.PickItTheme

@Composable
fun CinemaSelectorUI(
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFF2C2C2C)
    var selectedCinema by remember { mutableStateOf("Select Cinema") }
    var selectedLocation by remember { mutableStateOf("Select Governorate") }

    var expandedCinema by remember { mutableStateOf(false) }
    var expandedLocation by remember { mutableStateOf(false) }

    val cinemas = listOf(
        stringResource(R.string.cinema_vox_mall_of_egypt),
        stringResource(R.string.cinema_vox_almaza),
        stringResource(R.string.cinema_vox_alexandria),
        stringResource(R.string.cinema_renaissance_dandy),
        stringResource(R.string.cinema_renaissance_downtown),
        stringResource(R.string.cinema_zamalek),
        stringResource(R.string.cinema_galaxy_gezira),
        stringResource(R.string.cinema_cinepolis_americana_plaza),
        stringResource(R.string.cinema_imax_mall_of_arabia),
        stringResource(R.string.cinema_miami_alexandria),
        stringResource(R.string.cinema_odeon_cairo),
        stringResource(R.string.cinema_karim_downtown),
        stringResource(R.string.cinema_metro_cairo),
        stringResource(R.string.cinema_cinemax_mansoura),
        stringResource(R.string.cinema_royal_tanta)
    )

    val locations = listOf(
        stringResource(R.string.location_cairo),
        stringResource(R.string.location_giza),
        stringResource(R.string.location_alexandria),
        stringResource(R.string.location_qalyubia),
        stringResource(R.string.location_dakahlia),
        stringResource(R.string.location_beheira),
        stringResource(R.string.location_kafr_el_sheikh),
        stringResource(R.string.location_gharbia),
        stringResource(R.string.location_monufia),
        stringResource(R.string.location_sharqia),
        stringResource(R.string.location_damietta),
        stringResource(R.string.location_port_said),
        stringResource(R.string.location_ismailia),
        stringResource(R.string.location_suez),
        stringResource(R.string.location_north_sinai),
        stringResource(R.string.location_south_sinai),
        stringResource(R.string.location_beni_suef),
        stringResource(R.string.location_fayoum),
        stringResource(R.string.location_minya),
        stringResource(R.string.location_assiut),
        stringResource(R.string.location_sohag),
        stringResource(R.string.location_qena),
        stringResource(R.string.location_luxor),
        stringResource(R.string.location_aswan),
        stringResource(R.string.location_red_sea),
        stringResource(R.string.location_new_valley),
        stringResource(R.string.location_matrouh)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.height(75.dp),
            shape = RoundedCornerShape(20),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            )
        }

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            DropdownCard(
                label = "Cinema",
                selectedItem = selectedCinema,
                expanded = expandedCinema,
                items = cinemas,
                onExpand = { expandedCinema = true },
                onDismiss = { expandedCinema = false },
                onSelect = {
                    selectedCinema = it
                    expandedCinema = false
                },
                backgroundColor = backgroundColor,
                modifier = Modifier.fillMaxWidth(0.5f)
            )


            DropdownCard(
                label = "Location",
                selectedItem = selectedLocation,
                expanded = expandedLocation,
                items = locations,
                onExpand = { expandedLocation = true },
                onDismiss = { expandedLocation = false },
                onSelect = {
                    selectedLocation = it
                    expandedLocation = false
                },
                backgroundColor = backgroundColor,
                modifier = Modifier.fillMaxWidth(1f)
            )
        }
    }
}

@Composable
private fun DropdownCard(
    label: String,
    selectedItem: String,
    expanded: Boolean,
    items: List<String>,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = onExpand
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    label,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Icon(
                    Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Text(
                selectedItem,
                color = Color.Gray,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = onDismiss,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(horizontal = 8.dp)
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = { onSelect(item) }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CinemaSelectorUiPreview() {
    PickItTheme {
        CinemaSelectorUI()
    }

}
package com.hitech.pickit.media.presentation.screens.cinemas_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hitech.pickit.core.presentation.theme.PickItTheme

data class DateCard(
    val day: String,
    val date: String
)

@Composable
fun DayCalendarItem(
    dayName: String,
    dateNumber: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color(0xFF2C2C2C)
    val borderColor = if (isSelected) Color.White else Color.Transparent
    val textColor = Color.White
    val dayNameColor = Color.LightGray

    Box(
        modifier = Modifier
            .width(55.dp)
            .height(75.dp)
            .padding(horizontal = 2.dp)
            .border(
                border = BorderStroke(1.dp, borderColor),
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = dayName,
                fontSize = 14.sp,
                color = dayNameColor,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = dateNumber,
                style = MaterialTheme.typography.headlineMedium,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun WeekCalendarRow() {
    val daysData = listOf(
        DateCard("SU", "12"),
        DateCard("MO", "13"),
        DateCard("TU", "14"),
        DateCard("WE", "15"),
        DateCard("TH", "16"),
        DateCard("FR", "17")
    )

    var selectedDay by remember { mutableStateOf("SU") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        daysData.forEach { dateCard ->
            DayCalendarItem(
                dayName = dateCard.day,
                dateNumber = dateCard.date,
                isSelected = selectedDay == dateCard.day,
                onClick = { selectedDay = dateCard.day }
            )
        }
    }
}

@Preview
@Composable
fun PreviewCalendarRow() {
    PickItTheme {
        WeekCalendarRow()
    }
}
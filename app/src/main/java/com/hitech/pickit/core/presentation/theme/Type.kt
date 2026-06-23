package com.hitech.pickit.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hitech.pickit.R


val Poppins = FontFamily(
    Font(
        resId = R.font.poppins_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.poppins_semibold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_bolditalic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.poppins_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.poppins_mediumitalic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.poppins_regular,
        weight = FontWeight.Normal,
    ),
    Font(
        resId = R.font.poppins_light,
        weight = FontWeight.Light,
    )
)


val Typography = Typography(
    //  Main movie title (banner)
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 36.sp
    ),

    //  Movie title in lists or cards
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),

    //  Movie description or overview text
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    //  Genre tags (Action, Drama, Comedy,....)
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),

    //  Additional movie info (duration, rating, year)
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp
    ),
    //  Buttons or call-to-action text
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    ),

    //  Search bar text or secondary captions
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)
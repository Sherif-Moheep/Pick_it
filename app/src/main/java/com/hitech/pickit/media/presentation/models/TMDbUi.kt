package com.hitech.pickit.media.presentation.models

import android.icu.text.NumberFormat
import com.hitech.pickit.media.domain.model.Movie
import java.util.Locale

data class TMDbUi(
    val id: Int,
    val name: String,
    val picture: String?,
    val rate: DisplayableNumber,
    val category: List<Int>,
    val overview: String,
    val releaseDate: String?,
    val voteCount: Int

)

data class DisplayableNumber(
    val value: Double,
    val formatted: String

)

fun Movie.toTMDbUi(): TMDbUi {
    return TMDbUi(
        id = id,
        name = name,
        picture = posterUrl,
        rate = voteAverage.toDisplayableNumber(),
        category = genreIds,
        overview = overview,
        releaseDate = releaseDate,
        voteCount = voteCount
    )


}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatted = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 1
        minimumFractionDigits = 1
    }
    return DisplayableNumber(
        value = this,
        formatted = formatted.format(this)
    )

}

package com.hitech.pickit.media.data.datasource.remote.mappers

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.hitech.pickit.R
import com.hitech.pickit.core.data.datasource.local.entity.MovieEntity
import com.hitech.pickit.core.data.datasource.local.entity.TVShowEntity
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.presentation.utils.getCategoryFromId
import com.hitech.pickit.media.data.datasource.remote.dto.MovieResponse
import com.hitech.pickit.media.data.util.Constants.BASE_WIDTH_500_PATH
import com.hitech.pickit.media.domain.model.Cast
import com.hitech.pickit.media.domain.model.Crew
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.presentation.models.Actor
import com.hitech.pickit.media.presentation.models.Credit
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.models.TMDbUi
import com.hitech.pickit.media.presentation.models.toDisplayableNumber
import kotlin.collections.emptyList


fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = id,
        name = name,
        overview = overview,
        posterUrl = if (!posterPath.isNullOrEmpty()) posterPath else null,
        backdropUrl = if (!backdropPath.isNullOrEmpty()) backdropPath else null,       voteAverage = voteAverage,
        genreIds = genreIds,
        releaseDate = releaseDate,
        voteCount = voteCount,

        )
}
@Composable
fun NetworkError.toUserMessage(): String {
    val context = LocalContext.current

    return when (this) {
        NetworkError.REQUEST_TIMEOUT -> context.getString(R.string.error_request_timeout)
        NetworkError.TOO_MANY_REQUESTS -> context.getString(R.string.too_many_requests)
        NetworkError.NO_INTERNET -> context.getString(R.string.no_internet)
        NetworkError.SERVER_ERROR -> context.getString(R.string.server_error)
        NetworkError.SERIALIZATION -> context.getString(R.string.serialization_error)
        NetworkError.UNKNOW -> context.getString(R.string.unknow_error)
    }
}

fun Credit.toActor(): Actor {
    return Actor(
        id = id.toString().toInt(),
        role = role,
        name = name,
        profileUrl = profileUrl,
        gender = gender
    )
}
fun Cast.asActor(): Actor {
    return Actor(
        id = id,
        name = name,
        profileUrl = profileUrl,
        role = role,
        gender = gender,
    )
}
fun Crew.asActor(): Actor {
    return Actor(
        id = id,
        name = name,
        profileUrl = profileUrl,
        role = role,
        gender = gender,
    )
}
fun MovieEntity.asDomainModel(): Movie {
    return Movie(
        id = id,
        name = name,
        overview = overview,
        posterUrl = BASE_WIDTH_500_PATH+posterUrl,
        backdropUrl = BASE_WIDTH_500_PATH+posterUrl,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        genreIds = emptyList()
    )
}
fun TVShowEntity.asDomainModel(): TVShow {
    return TVShow(
        id = id,
        name = name,
        overview = overview,
        posterUrl = if (!posterUrl.isNullOrEmpty()) posterUrl else null,
        backdropUrl = if (!backdropUrl.isNullOrEmpty()) backdropUrl else null,
        releaseDate = releaseDate,
        voteAverage =voteAverage,
        voteCount = voteCount,
        genreIds = emptyList()
    )
}


fun TMDbItem.toCommonUiModel(): TMDbUi {
    return when (this) {
        is Movie -> TMDbUi(
            id = id,
            name = name,
            picture = posterUrl,
            rate = voteAverage.toDisplayableNumber(),
            releaseDate = releaseDate ?: "",
            overview = overview,
            voteCount = voteCount,
            category = genreIds.map { getCategoryFromId(it) }
        )

        is TVShow -> TMDbUi(
            id = id,
            name =name,
            picture = posterUrl,
            rate = voteAverage.toDisplayableNumber(),
            releaseDate = releaseDate ?: "",
            overview = overview,
            voteCount = voteCount,
            category = genreIds.map { getCategoryFromId(it) }
        )

        else -> TMDbUi(
            0, "", null, 0.0.toDisplayableNumber(), emptyList(), "", "", 0
        )
    }
}
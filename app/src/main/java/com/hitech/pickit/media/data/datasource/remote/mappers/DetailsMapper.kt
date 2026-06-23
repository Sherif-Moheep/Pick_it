package com.hitech.pickit.media.data.datasource.remote.mappers

import com.hitech.pickit.media.data.datasource.remote.dto.GenreResponse
import com.hitech.pickit.media.data.datasource.remote.dto.MovieDetailResponse
import com.hitech.pickit.media.data.datasource.remote.dto.SpokenLanguageResponse
import com.hitech.pickit.media.data.datasource.remote.dto.TvDetailResponse
import com.hitech.pickit.media.data.util.Constants.BASE_WIDTH_342_PATH
import com.hitech.pickit.media.data.util.Constants.BASE_WIDTH_780_PATH
import com.hitech.pickit.media.domain.model.Genre
import com.hitech.pickit.media.domain.model.MovieDetails
import com.hitech.pickit.media.domain.model.SpokenLanguage
import com.hitech.pickit.media.domain.model.TVShowDetails

fun MovieDetailResponse.asDomainModel(): MovieDetails = MovieDetails(
    backdropPath = if (!backdropPath.isNullOrEmpty()) {
        BASE_WIDTH_780_PATH + backdropPath
    } else null,

    genres = genres.asGenreDomainModel(),
    homepage = homepage,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,

    posterPath = if (!posterPath.isNullOrEmpty()) {
        BASE_WIDTH_342_PATH + posterPath
    } else null,

    releaseDate = releaseDate,
    spokenLanguages = spokenLanguages.asLanguageDomainModel(),

    status = status ?: "",
    tagline = tagline ?: "",

    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

fun TvDetailResponse.asDomainModel(): TVShowDetails = TVShowDetails(
    backdropPath = if (!backdropPath.isNullOrEmpty()) {
        BASE_WIDTH_780_PATH + backdropPath
    } else null,
    genres = genres.asGenreDomainModel(),
    homepage = homepage,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = if (!posterPath.isNullOrEmpty()) {
        BASE_WIDTH_342_PATH + posterPath
    } else null,
    releaseDate = releaseDate,
    spokenLanguages = spokenLanguages.asLanguageDomainModel(),
    status = status ?: "",
    tagline = tagline ?: "",
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

private fun List<GenreResponse>.asGenreDomainModel(): List<Genre> = map {
    Genre(it.id, it.name)
}

private fun List<SpokenLanguageResponse>.asLanguageDomainModel(): List<SpokenLanguage> = map {
    SpokenLanguage(it.iso6391, it.name)
}
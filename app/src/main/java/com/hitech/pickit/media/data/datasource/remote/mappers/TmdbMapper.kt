package com.hitech.pickit.media.data.datasource.remote.mappers

import com.hitech.pickit.media.data.datasource.remote.dto.MovieResponse
import com.hitech.pickit.media.data.datasource.remote.dto.TVShowResponse
import com.hitech.pickit.media.data.util.Constants.BASE_WIDTH_342_PATH
import com.hitech.pickit.media.data.util.Constants.BASE_WIDTH_780_PATH
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.domain.model.TVShow

fun List<MovieResponse>.asMovieDomainModel() = map(MovieResponse::asMovieDomainModel)

fun List<TVShowResponse>.asTVShowDomainModel() = map(TVShowResponse::asTVShowDomainModel)
private fun MovieResponse.asMovieDomainModel(): Movie = Movie(
    id = id,
    overview = overview,
    releaseDate = releaseDate,

    posterUrl = posterPath?.let { path ->
        BASE_WIDTH_342_PATH + path
    },

    backdropUrl = backdropPath?.let { path ->
        BASE_WIDTH_780_PATH + path
    },

    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
    genreIds = genreIds
)

private fun TVShowResponse.asTVShowDomainModel(): TVShow = TVShow(
    id = id,
    overview = overview,
    releaseDate = releaseDate,

    posterUrl = posterPath?.let { path ->
        BASE_WIDTH_342_PATH + path
    },

    backdropUrl = backdropPath?.let { path ->
        BASE_WIDTH_780_PATH + path
    },

    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
    genreIds = genreIds
)
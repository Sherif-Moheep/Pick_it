package com.hitech.pickit.media.domain.model

import com.hitech.pickit.media.presentation.models.TMDbItem


data class Movie(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double,
    override val voteCount: Int,
    override val genreIds: List<Int>,
): TMDbItem


data class TVShow(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double,
    override val voteCount: Int,
    override val genreIds: List<Int>,
) : TMDbItem

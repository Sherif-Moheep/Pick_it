package com.hitech.pickit.media.data.datasource.remote.dto

import com.hitech.pickit.media.data.util.Constants.BACKDROP_PATH
import com.hitech.pickit.media.data.util.Constants.FIRST_AIR_DATE
import com.hitech.pickit.media.data.util.Constants.GENRE_IDS
import com.hitech.pickit.media.data.util.Constants.ID
import com.hitech.pickit.media.data.util.Constants.NAME
import com.hitech.pickit.media.data.util.Constants.OVERVIEW
import com.hitech.pickit.media.data.util.Constants.POSTER_PATH
import com.hitech.pickit.media.data.util.Constants.TITLE
import com.hitech.pickit.media.data.util.Constants.VOTE_AVERAGE
import com.hitech.pickit.media.data.util.Constants.VOTE_COUNT
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface TMDbItemResponse {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
    val voteCount: Int

    val genreIds: List<Int>
}

@Serializable
data class MovieResponse(
    @SerialName(ID)
    override val id: Int,
    @SerialName(OVERVIEW)
    override val overview: String,
    @SerialName(RELEASE_DATE)
    override val releaseDate: String?,
    @SerialName(POSTER_PATH)
    override val posterPath: String?,
    @SerialName(BACKDROP_PATH)
    override val backdropPath: String?,
    @SerialName(TITLE)
    override val name: String,
    @SerialName(VOTE_AVERAGE)
    override val voteAverage: Double,
    @SerialName(VOTE_COUNT)
    override val voteCount: Int,
    @SerialName(GENRE_IDS)
    override val genreIds: List<Int>,
) : TMDbItemResponse

@Serializable
data class TVShowResponse(
    @SerialName(ID)
    override val id: Int,
    @SerialName(OVERVIEW)
    override val overview: String,
    @SerialName(FIRST_AIR_DATE)
    override val releaseDate: String?,
    @SerialName(POSTER_PATH)
    override val posterPath: String?,
    @SerialName(BACKDROP_PATH)
    override val backdropPath: String?,
    @SerialName(NAME)
    override val name: String,
    @SerialName(VOTE_AVERAGE)
    override val voteAverage: Double,
    @SerialName(VOTE_COUNT)
    override val voteCount: Int,
    @SerialName(GENRE_IDS)
    override val genreIds: List<Int>
) : TMDbItemResponse
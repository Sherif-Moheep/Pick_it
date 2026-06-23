package com.hitech.pickit.media.data.datasource.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val BACKDROP_PATH = "backdrop_path"
private const val GENRES = "genres"
private const val HOMEPAGE = "homepage"
private const val ID = "id"
private const val ORIGINAL_LANGUAGE = "original_language"
private const val ORIGINAL_TITLE = "original_title"
private const val ORIGINAL_NAME = "original_name"
private const val OVERVIEW = "overview"
private const val POPULARITY = "popularity"
private const val POSTER_PATH = "poster_path"
const val RELEASE_DATE = "release_date"
private const val FIRST_AIR_DATE = "first_air_date"
private const val SPOKEN_LANGUAGE = "spoken_languages"
private const val STATUS = "status"
private const val TAGLINE = "tagline"
private const val TITLE = "title"
private const val NAME = "name"
private const val VOTE_AVERAGE = "vote_average"
private const val VOTE_COUNT = "vote_count"

interface TMDbItemDetailsResponse {
    val backdropPath: String?
    val genres: List<GenreResponse>
    val homepage: String?
    val id: Int
    val originalLanguage: String
    val originalTitle: String
    val overview: String
    val popularity: Double
    val posterPath: String?
    val releaseDate: String?
    val spokenLanguages: List<SpokenLanguageResponse>
    val status: String?
    val tagline: String?
    val title: String
    val voteAverage: Double
    val voteCount: Int
}

@Serializable
data class MovieDetailResponse(
    @SerialName(BACKDROP_PATH) override val backdropPath: String? = null,
    @SerialName(GENRES) override val genres: List<GenreResponse> = emptyList(),
    @SerialName(HOMEPAGE) override val homepage: String? = null,
    @SerialName(ID) override val id: Int,
    @SerialName(ORIGINAL_LANGUAGE) override val originalLanguage: String = "",
    @SerialName(ORIGINAL_TITLE) override val originalTitle: String = "",
    @SerialName(OVERVIEW) override val overview: String = "",
    @SerialName(POPULARITY) override val popularity: Double = 0.0,
    @SerialName(POSTER_PATH) override val posterPath: String? = null,
    @SerialName(RELEASE_DATE) override val releaseDate: String? = null,
    @SerialName(SPOKEN_LANGUAGE) override val spokenLanguages: List<SpokenLanguageResponse> = emptyList(),
    @SerialName(STATUS) override val status: String? = null,
    @SerialName(TAGLINE) override val tagline: String? = null,
    @SerialName(TITLE) override val title: String = "",
    @SerialName(VOTE_AVERAGE) override val voteAverage: Double = 0.0,
    @SerialName(VOTE_COUNT) override val voteCount: Int = 0,
) : TMDbItemDetailsResponse

@Serializable
data class TvDetailResponse(
    @SerialName(BACKDROP_PATH) override val backdropPath: String? = null,
    @SerialName(GENRES) override val genres: List<GenreResponse> = emptyList(),
    @SerialName(HOMEPAGE) override val homepage: String? = null,
    @SerialName(ID) override val id: Int,
    @SerialName(ORIGINAL_LANGUAGE) override val originalLanguage: String = "",
    @SerialName(ORIGINAL_NAME) override val originalTitle: String = "",
    @SerialName(OVERVIEW) override val overview: String = "",
    @SerialName(POPULARITY) override val popularity: Double = 0.0,
    @SerialName(POSTER_PATH) override val posterPath: String? = null,
    @SerialName(FIRST_AIR_DATE) override val releaseDate: String? = null,
    @SerialName(SPOKEN_LANGUAGE) override val spokenLanguages: List<SpokenLanguageResponse> = emptyList(),
    @SerialName(STATUS) override val status: String? = null,
    @SerialName(TAGLINE) override val tagline: String? = null,
    @SerialName(NAME) override val title: String = "",
    @SerialName(VOTE_AVERAGE) override val voteAverage: Double = 0.0,
    @SerialName(VOTE_COUNT) override val voteCount: Int = 0,
) : TMDbItemDetailsResponse

@Serializable
data class GenreResponse(
    @SerialName(ID) val id: Int,
    @SerialName(NAME) val name: String? = null
)

@Serializable
data class SpokenLanguageResponse(
    @SerialName("iso_639_1") val iso6391: String,
    @SerialName(NAME) val name: String = ""
)
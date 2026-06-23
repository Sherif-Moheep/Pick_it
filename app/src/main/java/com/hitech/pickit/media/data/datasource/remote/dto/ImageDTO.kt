package com.hitech.pickit.media.data.datasource.remote.dto

import com.hitech.pickit.media.data.util.Constants.ID
import com.hitech.pickit.media.data.util.Constants.VOTE_AVERAGE
import com.hitech.pickit.media.data.util.Constants.VOTE_COUNT
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val BACKDROPS = "backdrops"
private const val POSTERS = "posters"
private const val ASPECT_RATIO = "aspect_ratio"
private const val FILE_PATH = "file_path"
private const val HEIGHT = "height"
private const val ISO_639_1 = "iso_639_1"
private const val WIDTH = "width"

@Serializable
data class ImagesResponse(
    @SerialName( BACKDROPS) val backdrops: List<ImageResponse>,
    @SerialName( ID) val id: Int,
    @SerialName( POSTERS) val posters: List<ImageResponse>,
)

@Serializable
data class ImageResponse(
    @SerialName( ASPECT_RATIO) val aspectRatio: Double,
    @SerialName( FILE_PATH) val filePath: String,
    @SerialName( HEIGHT) val height: Int,
    @SerialName( ISO_639_1) val iso6391: String?,
    @SerialName( VOTE_AVERAGE) val voteAverage: Double,
    @SerialName( VOTE_COUNT) val voteCount: Int,
    @SerialName( WIDTH) val width: Int,
)
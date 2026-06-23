package com.hitech.pickit.media.data.datasource.remote.dto

import com.hitech.pickit.media.data.util.Constants.ID
import com.hitech.pickit.media.data.util.Constants.NAME
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastResponse(
    @SerialName( "character")
    val role: String,
    @SerialName( NAME)
    val name: String,
    @SerialName( PROFILE_PATH)
    val profilePath: String?,
    @SerialName( GENDER)
    val gender: Int,
    @SerialName( ID)
    val id: Int,
)

@Serializable
data class CrewResponse(
    @SerialName( "job")
    val role: String,
    @SerialName( NAME)
    val name: String,
    @SerialName( PROFILE_PATH)
    val profilePath: String?,
    @SerialName( GENDER)
    val gender: Int,
    @SerialName(ID)
    val id: Int,
)

private const val PROFILE_PATH = "profile_path"
private const val GENDER = "gender"

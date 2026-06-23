package com.hitech.pickit.media.data.datasource.remote.dto

import com.hitech.pickit.media.data.util.Constants.ID
import com.hitech.pickit.media.data.util.Constants.NAME
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDTO(
    @SerialName("birthday")
    val birthDay: String?,
    @SerialName("deathday")
    val deathDay: String?,
    @SerialName(ID)
    val id: Int,
    @SerialName(NAME)
    val name: String,
    @SerialName("biography")
    val biography: String,
    @SerialName("place_of_birth")
    val placeOfBirth: String?,
    @SerialName("profile_path")
    val profilePath: String?,
)

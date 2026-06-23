package com.hitech.pickit.media.data.datasource.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTMDbWrapper<T : TMDbItemResponse>(
    @SerialName("results")
    val items: List<T>,
)

@Serializable
data class NetworkCreditWrapper(
    @SerialName("cast")
    val cast: List<CastResponse>,
    @SerialName("crew")
    val crew: List<CrewResponse>,
)

package com.hitech.pickit.media.data.datasource.remote.mappers

import com.hitech.pickit.media.data.datasource.remote.dto.ImagesResponse
import com.hitech.pickit.media.data.util.Constants.BASE_IMAGE_PATH
import com.hitech.pickit.media.domain.model.TMDbImage

fun ImagesResponse.asDomainModel(): List<TMDbImage> = buildList {
    addAll(backdrops.map {
        TMDbImage(
            url = BASE_IMAGE_PATH + it.filePath,
            voteCount = it.voteCount
        )
    })

    addAll(posters.map {
        TMDbImage(
            url = BASE_IMAGE_PATH + it.filePath,
            voteCount = it.voteCount
        )
    })

    sortByDescending { it.voteCount }
}
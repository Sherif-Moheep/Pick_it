package com.hitech.pickit.media.data.datasource.remote.mappers

import com.hitech.pickit.media.data.datasource.remote.dto.PersonDTO
import com.hitech.pickit.media.data.util.Constants.BASE_WIDTH_342_PATH
import com.hitech.pickit.media.domain.model.Person

fun PersonDTO.asDomainModel(): Person = Person(
    birthDay,
    deathDay,
    id,
    name,
    biography,
    placeOfBirth,
    profilePath?.let { profilePath ->
        BASE_WIDTH_342_PATH + profilePath

    },
)
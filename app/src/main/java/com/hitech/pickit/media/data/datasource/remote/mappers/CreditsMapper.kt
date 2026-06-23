package com.hitech.pickit.media.data.datasource.remote.mappers

import com.hitech.pickit.media.data.datasource.remote.dto.CastResponse
import com.hitech.pickit.media.data.datasource.remote.dto.CrewResponse
import com.hitech.pickit.media.data.util.Constants.BASE_WIDTH_342_PATH
import com.hitech.pickit.media.domain.model.Gender
import com.hitech.pickit.media.domain.model.Cast
import com.hitech.pickit.media.domain.model.Crew

fun List<CastResponse>.asCastDomainModel() = map(CastResponse::asCastDomainModel)

fun List<CrewResponse>.asCrewDomainModel() = map(CrewResponse::asCrewDomainModel)

private fun CastResponse.asCastDomainModel(): Cast = Cast(
    role = role,
    name = name,
    profileUrl = BASE_WIDTH_342_PATH+profilePath,
    gender = gender.toGender(),
    id = id,
)

private fun CrewResponse.asCrewDomainModel(): Crew = Crew(
    role = role,
    name = name,
    profileUrl = BASE_WIDTH_342_PATH+profilePath,
    gender = gender.toGender(),
    id = id,
)

private fun Int.toGender() = if (this == 1) Gender.FEMALE else Gender.MALE
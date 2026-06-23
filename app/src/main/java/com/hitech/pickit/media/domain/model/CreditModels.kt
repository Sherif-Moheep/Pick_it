package com.hitech.pickit.media.domain.model

import com.hitech.pickit.media.presentation.models.Credit

class Cast(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: Int,
) : Credit


class Crew(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: Int,
) : Credit

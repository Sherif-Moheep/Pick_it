package com.hitech.pickit.media.presentation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman
import androidx.compose.ui.graphics.vector.ImageVector
import com.hitech.pickit.media.domain.model.Gender

val Gender.placeholderIcon: ImageVector
    get() = when (this) {
        Gender.MALE -> Icons.Rounded.Man
        Gender.FEMALE -> Icons.Rounded.Woman
    }

interface Credit {
    val id: Any
    val role: String
    val name: String
    val profileUrl: String?
    val gender: Gender
}

open class Actor(
    override val id: Int,
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender
) : Credit


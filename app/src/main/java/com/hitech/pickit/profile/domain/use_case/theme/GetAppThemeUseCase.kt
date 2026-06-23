package com.hitech.pickit.profile.domain.use_case.theme


import com.hitech.pickit.profile.domain.repository.ProfileRepository
import com.hitech.pickit.profile.presentation.util.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppThemeUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): Flow<AppTheme> = repository.appTheme
}
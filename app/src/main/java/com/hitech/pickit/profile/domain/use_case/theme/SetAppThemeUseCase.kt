package com.hitech.pickit.profile.domain.use_case.theme



import com.hitech.pickit.profile.domain.repository.ProfileRepository
import com.hitech.pickit.profile.presentation.util.AppTheme
import javax.inject.Inject

class SetAppThemeUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(theme: AppTheme) {
        repository.saveThemePreference(theme)
    }
}
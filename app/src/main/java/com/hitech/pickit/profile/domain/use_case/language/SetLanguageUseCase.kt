package com.hitech.pickit.profile.domain.use_case.language


import com.hitech.pickit.profile.domain.repository.ProfileRepository
import com.hitech.pickit.profile.presentation.util.AppLanguage
import javax.inject.Inject

class SetLanguageUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(language: AppLanguage) {
        repository.setLanguage(language)
    }
}
package com.hitech.pickit.profile.domain.use_case.language


import com.hitech.pickit.profile.domain.repository.ProfileRepository
import com.hitech.pickit.profile.presentation.util.AppLanguage
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(): AppLanguage {
        return repository.getCurrentLanguage()
    }
}
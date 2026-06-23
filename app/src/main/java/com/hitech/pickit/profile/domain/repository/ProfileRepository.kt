package com.hitech.pickit.profile.domain.repository

import com.hitech.pickit.profile.presentation.util.AppLanguage
import com.hitech.pickit.profile.presentation.util.AppTheme
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    val appTheme: Flow<AppTheme>

    suspend fun saveThemePreference(theme: AppTheme)

    fun getCurrentLanguage(): AppLanguage

    suspend fun setLanguage(language: AppLanguage)
}
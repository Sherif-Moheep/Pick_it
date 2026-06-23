package com.hitech.pickit.profile.data.repository

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.hitech.pickit.profile.domain.repository.ProfileRepository
import com.hitech.pickit.profile.data.datasource.LocalDataSource
import com.hitech.pickit.profile.presentation.util.AppLanguage
import com.hitech.pickit.profile.presentation.util.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): ProfileRepository {

    override val appTheme: Flow<AppTheme>
        get() = localDataSource.themeMode

    override suspend fun saveThemePreference(theme: AppTheme) {
        localDataSource.saveThemePreference(theme)
    }

    override fun getCurrentLanguage(): AppLanguage {
        val currentLocale = AppCompatDelegate.getApplicationLocales().get(0)
        val code = currentLocale?.language ?: "en"
        return AppLanguage.Companion.getByCode(code)
    }

    override suspend fun setLanguage(language: AppLanguage) {
        val appLocale = LocaleListCompat.forLanguageTags(language.code)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}
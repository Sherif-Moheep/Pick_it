package com.hitech.pickit.profile.data.datasource

import com.hitech.pickit.profile.data.datasource.datastore.PreferenceDataStore
import com.hitech.pickit.profile.presentation.util.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val preferenceDataStore: PreferenceDataStore
) {
    val themeMode: Flow<AppTheme>
        get() = preferenceDataStore.themeMode

    suspend fun saveThemePreference(theme: AppTheme) {
        preferenceDataStore.saveThemePreference(theme)
    }
}
package com.hitech.pickit.profile.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hitech.pickit.profile.data.util.Constants.PREFERENCES_NAME
import com.hitech.pickit.profile.data.util.Constants.PREFERENCES_THEME_KEY
import com.hitech.pickit.profile.presentation.util.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)
        private val THEME_KEY = stringPreferencesKey(PREFERENCES_THEME_KEY)
    }

    // reads the theme preference from the DataStore, defaults to LIGHT if no value is set
    val themeMode: Flow<AppTheme> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val themeName = preferences[THEME_KEY] ?: AppTheme.DARK.name

            // Convert the string back to your Enum
            try {
                AppTheme.valueOf(themeName)
            } catch (e: IllegalArgumentException) {
                // Safety fallback if the data gets corrupted or enum name changes
                AppTheme.DARK
            }
        }

    // saves the theme preference to the DataStore
    suspend fun saveThemePreference(appTheme: AppTheme) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = appTheme.name
        }
    }
}
package com.hitech.pickit.profile.presentation.viewmodel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hitech.pickit.auth.domain.model.UserData
import com.hitech.pickit.auth.domain.repository.AuthRepository
import com.hitech.pickit.profile.domain.use_case.language.GetLanguageUseCase
import com.hitech.pickit.profile.domain.use_case.language.SetLanguageUseCase
import com.hitech.pickit.profile.domain.use_case.theme.GetAppThemeUseCase
import com.hitech.pickit.profile.domain.use_case.theme.SetAppThemeUseCase
import com.hitech.pickit.profile.presentation.util.AppLanguage
import com.hitech.pickit.profile.presentation.util.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    // User's info Block
    private val _user = MutableStateFlow<UserData?>(null)
    val user = _user.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        _user.value = authRepository.getSignedInUser()
    }

    fun signOut(context: Context) {
        viewModelScope.launch {
            authRepository.signOut(context)
        }
    }

    // theme block
    val theme: StateFlow<AppTheme> = getAppThemeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = AppTheme.LIGHT
        )

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            setAppThemeUseCase(theme)
        }
    }

    // Language Block
    private val _currentLanguage = MutableStateFlow(getLanguageUseCase())
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    fun setLanguage(language: AppLanguage) {
        viewModelScope.launch {
            setLanguageUseCase(language)
            _currentLanguage.value = language
        }
    }
}
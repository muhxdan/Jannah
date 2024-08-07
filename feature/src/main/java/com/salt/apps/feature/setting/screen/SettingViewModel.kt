package com.salt.apps.feature.setting.screen

import android.app.Application
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salt.apps.core.R
import com.salt.apps.core.domain.model.ThemeConfig
import com.salt.apps.core.domain.usecase.SettingUseCase
import com.salt.apps.feature.setting.screen.components.SettingEvent
import com.salt.apps.feature.setting.screen.components.SettingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val context: Application,
    private val settingUseCase: SettingUseCase
) : ViewModel() {

    private val _settingState = MutableStateFlow(SettingState())
    val settingState: StateFlow<SettingState> = _settingState.asStateFlow()

    init {
        getSetting()
    }

    private fun getSetting() {
        viewModelScope.launch {
            settingUseCase.setting.collect { setting ->
                _settingState.value = _settingState.value.copy(
                    themeConfig = setting.themeConfig,
                )
            }
        }
    }

    private fun updateTheme(themeConfig: ThemeConfig) {
        viewModelScope.launch {
            settingUseCase.setThemeConfig(themeConfig)
        }
    }

    fun onSettingEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.UpdateTheme -> updateTheme(themeConfig = event.themeConfig)
            is SettingEvent.ShareApp -> shareApp()
            is SettingEvent.ShowAbout -> showAbout()
            is SettingEvent.DismissAbout -> dismissAbout()
            is SettingEvent.ShowThemeDialog -> showThemeDialog()
            is SettingEvent.DismissThemeDialog -> dismissThemeDialog()
        }
    }

    private fun shareApp() {
        val appLink =
            "https://github.com/muhxdan/Jannah/releases"
        val shareMessage = context.getString(R.string.share_message, appLink)

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareMessage)
            type = "text/plain"
        }
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(shareIntent)
    }


    private fun showAbout() {
        _settingState.value = _settingState.value.copy(showAboutDialog = true)
    }

    private fun dismissAbout() {
        _settingState.value = _settingState.value.copy(showAboutDialog = false)
    }

    private fun showThemeDialog() {
        _settingState.value = _settingState.value.copy(showThemeDialog = true)
    }

    private fun dismissThemeDialog() {
        _settingState.value = _settingState.value.copy(showThemeDialog = false)
    }
}
package com.salt.apps.feature.setting.screen.components

import com.salt.apps.core.domain.model.ThemeConfig

sealed class SettingEvent {
    data class UpdateTheme(val themeConfig: ThemeConfig) : SettingEvent()
    data object ShareApp : SettingEvent()
    data object ShowAbout : SettingEvent()
    data object DismissAbout : SettingEvent()
    data object ShowThemeDialog : SettingEvent()
    data object DismissThemeDialog : SettingEvent()
}
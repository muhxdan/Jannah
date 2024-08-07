package com.salt.apps.feature.setting.screen.components

import com.salt.apps.core.domain.model.ThemeConfig

data class SettingState(
    val isThemeDark: Boolean = false,
    val showAboutDialog: Boolean = false,
    val showThemeDialog: Boolean = false,
    val themeConfig: ThemeConfig = ThemeConfig.FOLLOW_SYSTEM
)
package com.salt.apps.core.data.source.local.datastore

import androidx.datastore.core.DataStore
import com.salt.apps.core.datastore.ThemeConfigProto
import com.salt.apps.core.datastore.UserPreferences
import com.salt.apps.core.domain.model.Setting
import com.salt.apps.core.domain.model.ThemeConfig
import com.salt.apps.core.domain.model.ThemeConfig.DARK
import com.salt.apps.core.domain.model.ThemeConfig.FOLLOW_SYSTEM
import com.salt.apps.core.domain.model.ThemeConfig.LIGHT
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PreferencesDataStore @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val setting = userPreferences.data.map {
        Setting(
            themeConfig = when (it.themeConfig) {
                ThemeConfigProto.THEME_CONFIG_LIGHT -> LIGHT
                ThemeConfigProto.THEME_CONFIG_DARK -> DARK
                null,
                ThemeConfigProto.THEME_CONFIG_UNSPECIFIED,
                ThemeConfigProto.UNRECOGNIZED,
                ThemeConfigProto.THEME_CONFIG_FOLLOW_SYSTEM,
                -> FOLLOW_SYSTEM
            },
        )
    }

    suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        userPreferences.updateData { preferences ->
            preferences.toBuilder().setThemeConfig(
                when (themeConfig) {
                    FOLLOW_SYSTEM -> ThemeConfigProto.THEME_CONFIG_FOLLOW_SYSTEM
                    LIGHT -> ThemeConfigProto.THEME_CONFIG_LIGHT
                    DARK -> ThemeConfigProto.THEME_CONFIG_DARK
                }
            ).build()
        }
    }
}
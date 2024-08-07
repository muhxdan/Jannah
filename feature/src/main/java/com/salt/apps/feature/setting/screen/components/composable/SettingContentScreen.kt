package com.salt.apps.feature.setting.screen.components.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.salt.apps.core.R
import com.salt.apps.core.domain.model.SettingItem
import com.salt.apps.core.ui.design.components.CustomTopBar
import com.salt.apps.feature.setting.screen.components.SettingEvent
import com.salt.apps.feature.setting.screen.components.SettingEvent.DismissAbout
import com.salt.apps.feature.setting.screen.components.SettingEvent.DismissThemeDialog
import com.salt.apps.feature.setting.screen.components.SettingEvent.ShareApp
import com.salt.apps.feature.setting.screen.components.SettingEvent.ShowAbout
import com.salt.apps.feature.setting.screen.components.SettingEvent.ShowThemeDialog
import com.salt.apps.feature.setting.screen.components.SettingEvent.UpdateTheme
import com.salt.apps.feature.setting.screen.components.SettingState

@Composable
fun SettingContentScreen(settingState: SettingState, onSettingEvent: (SettingEvent) -> Unit) {
    Column {
        CustomTopBar("Setting")
        LazyColumn {
            items(settingItemList(onSettingEvent = onSettingEvent)) { setting ->
                SettingItemRow(setting = setting)
            }
        }
    }

    if (settingState.showThemeDialog) {
        ThemeDialog(
            currentTheme = settingState.themeConfig,
            onDismissRequest = { onSettingEvent(DismissThemeDialog) },
            onThemeSelected = { themeConfig ->
                onSettingEvent(UpdateTheme(themeConfig))

            },
        )
    }

    if (settingState.showAboutDialog) {
        AboutDialog(onDismiss = { onSettingEvent(DismissAbout) })
    }
}

private fun settingItemList(onSettingEvent: (SettingEvent) -> Unit): List<SettingItem> {
    return listOf(
        SettingItem(
            "Themes",
            R.drawable.ic_theme_dark,
            onClick = { onSettingEvent(ShowThemeDialog) },
        ),
        SettingItem(
            "Share",
            R.drawable.ic_share,
            onClick = { onSettingEvent(ShareApp) },
        ),
        SettingItem(
            "About",
            R.drawable.ic_information,
            onClick = { onSettingEvent(ShowAbout) },
        ),
    )
}
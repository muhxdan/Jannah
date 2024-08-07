package com.salt.apps.feature.setting.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.salt.apps.feature.setting.screen.components.composable.SettingContentScreen

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val settingState by settingViewModel.settingState.collectAsStateWithLifecycle()

    SettingContentScreen(
        settingState = settingState,
        onSettingEvent = settingViewModel::onSettingEvent
    )
}
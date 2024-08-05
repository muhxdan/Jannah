package com.salt.apps.jannah.presentation.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.salt.apps.core.ui.viewmodel.DetailEvent
import com.salt.apps.feature.alquran.navigation.alQuranScreen
import com.salt.apps.feature.detail.navigation.detailScreen
import com.salt.apps.feature.detail.navigation.navigateToDetail
import com.salt.apps.feature.home.navigation.HomeRoute
import com.salt.apps.feature.home.navigation.homeScreen
import com.salt.apps.feature.prayer.navigation.prayerScreen
import com.salt.apps.feature.setting.navigation.settingScreen
import com.salt.apps.jannah.presentation.app.AppState

@Composable
fun JannahNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavHost(
        navController = navController, startDestination = HomeRoute,

        modifier = modifier
    ) {
        homeScreen()
        alQuranScreen(
            onSurahClick = { id, name, audio ->
                navController.navigateToDetail()
                appState.detailViewModel.onDetailEvent(
                    DetailEvent.UpdateSelectedTab(
                        selectedTab = id - 1,
                        currentSurahName = name,
                        audioUrl = audio
                    )
                )
            }, sharedViewModel = appState.sharedViewModel
        )
        prayerScreen(prayerViewModel = appState.prayerViewModel)
        settingScreen()
        detailScreen(
            sharedViewModel = appState.sharedViewModel,
            detailViewModel = appState.detailViewModel,
            onNavigationBack = { appState.navController.popBackStack() })
    }
}

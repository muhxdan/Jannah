package com.salt.apps.jannah.presentation.navhost

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.salt.apps.feature.alquran.navigation.alQuranScreen
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
        navController = navController,
        startDestination = HomeRoute,
        enterTransition = { slideInVertically { it / 16 } + fadeIn() },
        exitTransition = { fadeOut(tween(25)) },
        popEnterTransition = { slideInVertically { it / 16 } + fadeIn() },
        popExitTransition = { fadeOut(tween(25)) },
        modifier = modifier
    ) {
        homeScreen()
        alQuranScreen()
        prayerScreen()
        settingScreen()
    }
}
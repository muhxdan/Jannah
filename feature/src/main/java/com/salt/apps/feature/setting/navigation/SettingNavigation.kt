package com.salt.apps.feature.setting.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.salt.apps.core.utils.getRouteClassName
import com.salt.apps.feature.alquran.navigation.AlQuranRoute
import com.salt.apps.feature.home.navigation.HomeRoute
import com.salt.apps.feature.prayer.navigation.PrayerRoute
import com.salt.apps.feature.setting.screen.SettingScreen
import kotlinx.serialization.Serializable

@Serializable
data object SettingRoute

fun NavController.navigateToSetting(navOptions: NavOptions) =
    navigate(route = SettingRoute, navOptions)

fun NavGraphBuilder.settingScreen(
) {
    composable<SettingRoute>(
        enterTransition = {
            when (initialState.destination.route) {
                getRouteClassName<AlQuranRoute>(), getRouteClassName<PrayerRoute>(), getRouteClassName<HomeRoute>() -> slideInHorizontally(
                    animationSpec = tween(350)
                ) { it }

                else -> null
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                getRouteClassName<AlQuranRoute>(), getRouteClassName<PrayerRoute>(), getRouteClassName<HomeRoute>() -> slideOutHorizontally(
                    animationSpec = tween(350)
                ) { it }

                else -> null
            }
        },
    ) {
        SettingScreen()
    }
}
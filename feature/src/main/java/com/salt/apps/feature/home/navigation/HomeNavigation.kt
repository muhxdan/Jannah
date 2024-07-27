package com.salt.apps.feature.home.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.salt.apps.core.utils.getRouteClassName
import com.salt.apps.feature.alquran.navigation.AlQuranRoute
import com.salt.apps.feature.home.screen.HomeScreen
import com.salt.apps.feature.prayer.navigation.PrayerRoute
import com.salt.apps.feature.setting.navigation.SettingRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
) {
    composable<HomeRoute>(enterTransition = {
        when (initialState.destination.route) {
            getRouteClassName<AlQuranRoute>(), getRouteClassName<PrayerRoute>(), getRouteClassName<SettingRoute>() -> slideInHorizontally(
                animationSpec = tween(350)
            ) { -it }

            else -> null
        }
    }, exitTransition = {
        when (targetState.destination.route) {
            getRouteClassName<AlQuranRoute>(), getRouteClassName<PrayerRoute>(), getRouteClassName<SettingRoute>() -> slideOutHorizontally(
                animationSpec = tween(350)
            ) { -it }

            else -> null
        }
    }) {
        HomeScreen()
    }
}
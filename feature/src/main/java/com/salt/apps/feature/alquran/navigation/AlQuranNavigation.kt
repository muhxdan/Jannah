package com.salt.apps.feature.alquran.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.salt.apps.core.utils.getRouteClassName
import com.salt.apps.core.viewmodel.SharedViewModel
import com.salt.apps.feature.alquran.screen.AlQuranScreen
import com.salt.apps.feature.home.navigation.HomeRoute
import com.salt.apps.feature.prayer.navigation.PrayerRoute
import com.salt.apps.feature.setting.navigation.SettingRoute
import kotlinx.serialization.Serializable

@Serializable
data object AlQuranRoute

fun NavController.navigateToAlQuran(navOptions: NavOptions) =
    navigate(route = AlQuranRoute, navOptions)

fun NavGraphBuilder.alQuranScreen(
    onSurahClick: (Int) -> Unit, sharedViewModel: SharedViewModel
) {
    composable<AlQuranRoute>(
        enterTransition = {
            when (initialState.destination.route) {
                getRouteClassName<HomeRoute>() -> slideInHorizontally(animationSpec = tween(350)) { it }
                getRouteClassName<PrayerRoute>(), getRouteClassName<SettingRoute>() -> slideInHorizontally(
                    animationSpec = tween(350)
                ) { -it }

                else -> null
            }
        },
        exitTransition = {
            when (targetState.destination.route) {

                getRouteClassName<HomeRoute>() -> slideOutHorizontally(animationSpec = tween(350)) { it }
                getRouteClassName<PrayerRoute>(), getRouteClassName<SettingRoute>() -> slideOutHorizontally(
                    animationSpec = tween(350)
                ) { -it }


                else -> null
            }
        },
    ) {
        AlQuranScreen(onSurahClick = onSurahClick, sharedViewModel = sharedViewModel)
    }
}
package com.salt.apps.jannah.presentation.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.salt.apps.core.viewmodel.SharedViewModel
import com.salt.apps.feature.alquran.navigation.AlQuranRoute
import com.salt.apps.feature.alquran.navigation.navigateToAlQuran
import com.salt.apps.feature.detail.navigation.DetailRoute
import com.salt.apps.feature.home.navigation.HomeRoute
import com.salt.apps.feature.home.navigation.navigateToHome
import com.salt.apps.feature.prayer.navigation.PrayerRoute
import com.salt.apps.feature.prayer.navigation.navigateToPrayer
import com.salt.apps.feature.setting.navigation.SettingRoute
import com.salt.apps.feature.setting.navigation.navigateToSetting
import com.salt.apps.jannah.presentation.navhost.Destination

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: SharedViewModel,
): AppState {
    return remember(navController, sharedViewModel) {
        AppState(
            navController = navController,
            sharedViewModel = sharedViewModel,
        )
    }
}


@Stable
class AppState(
    val navController: NavHostController,
    val sharedViewModel: SharedViewModel,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val destination: Destination?
        @Composable get() {
            with(currentDestination) {
                if (this?.hasRoute<HomeRoute>() == true) return Destination.HOME
                if (this?.hasRoute<AlQuranRoute>() == true) return Destination.ALQURAN
                if (this?.hasRoute<DetailRoute>() == true) return Destination.DETAIL_ALQURAN
                if (this?.hasRoute<PrayerRoute>() == true) return Destination.PRAYER
                if (this?.hasRoute<SettingRoute>() == true) return Destination.SETTING
            }
            return null
        }

    val topLevelDestination: List<Destination> = listOf(
        Destination.HOME, Destination.ALQURAN, Destination.PRAYER, Destination.SETTING
    )

    fun navigateToTopLevelDestination(topLevelDestination: Destination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                Destination.HOME -> navController.navigateToHome(navOptions = topLevelNavOptions)
                Destination.ALQURAN -> navController.navigateToAlQuran(
                    navOptions = topLevelNavOptions,
                    surah = ""
                )

                Destination.PRAYER -> navController.navigateToPrayer(navOptions = topLevelNavOptions)
                Destination.SETTING -> navController.navigateToSetting(navOptions = topLevelNavOptions)
                else -> {}
            }
        }
    }
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: Destination) =
    this?.hierarchy?.any {
        it.hasRoute(destination.route)
    } ?: false
package com.salt.apps.jannah.presentation.navhost

import com.salt.apps.core.R
import com.salt.apps.feature.alquran.navigation.AlQuranRoute
import com.salt.apps.feature.home.navigation.HomeRoute
import com.salt.apps.feature.prayer.navigation.PrayerRoute
import com.salt.apps.feature.setting.navigation.SettingRoute
import kotlin.reflect.KClass

enum class Destination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: String,
    val titleTextId: String,
    val route: KClass<*>,
) {
    HOME(
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outlined,
        iconTextId = "Home",
        titleTextId = "Home",
        route = HomeRoute::class,
    ),
    ALQURAN(
        selectedIcon = R.drawable.ic_al_quran_filled,
        unselectedIcon = R.drawable.ic_al_quran,
        iconTextId = "Al Quran",
        titleTextId = "Al Quran",
        route = AlQuranRoute::class,
    ),
    PRAYER(
        selectedIcon = R.drawable.ic_prayer_filled,
        unselectedIcon = R.drawable.ic_prayer,
        iconTextId = "Shalat",
        titleTextId = "Shalat",
        route = PrayerRoute::class,
    ),
    SETTING(
        selectedIcon = R.drawable.ic_setting_filled,
        unselectedIcon = R.drawable.ic_setting_outlined,
        iconTextId = "Setting",
        titleTextId = "Setting",
        route = SettingRoute::class,
    ),
}

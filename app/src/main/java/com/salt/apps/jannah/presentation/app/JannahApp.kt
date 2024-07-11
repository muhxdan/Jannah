package com.salt.apps.jannah.presentation.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.salt.apps.jannah.presentation.navhost.JannahNavHost


@Composable
fun JannahApp(
    appState: AppState,
) {
    val currentDestination = appState.currentDestination

    val myNavigationSuiteItemColors = NavigationBarItemDefaults.colors(
        indicatorColor = MaterialTheme.colorScheme.background,
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
    )

    val destination = appState.destination
    val isDestinationTopLevel = destination in appState.topLevelDestination

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = isDestinationTopLevel,
                enter = slideInVertically(
                    initialOffsetY = { it }, animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessLow
                    )
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it }, animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessLow
                    )
                ),
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    appState.topLevelDestination.forEach { destination ->
                        val selected =
                            currentDestination.isTopLevelDestinationInHierarchy(destination)
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (selected) {
                                        ImageVector.vectorResource(destination.selectedIcon)
                                    } else {
                                        ImageVector.vectorResource(destination.unselectedIcon)
                                    },
                                    contentDescription = null,
                                )
                            },
                            label = { Text(destination.iconTextId) },
                            onClick = { appState.navigateToTopLevelDestination(destination) },
                            colors = myNavigationSuiteItemColors,
                            selected = selected,
                        )
                    }
                }
            }
        },
    ) { padding ->
        JannahNavHost(
            appState = appState,
            modifier = Modifier.padding(padding),
        )
    }
}

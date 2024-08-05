package com.salt.apps.jannah.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.salt.apps.core.domain.model.ThemeConfig.DARK
import com.salt.apps.core.domain.model.ThemeConfig.FOLLOW_SYSTEM
import com.salt.apps.core.domain.model.ThemeConfig.LIGHT
import com.salt.apps.core.ui.design.theme.JannahTheme
import com.salt.apps.core.ui.viewmodel.SharedState
import com.salt.apps.core.ui.viewmodel.SharedViewModel
import com.salt.apps.jannah.presentation.app.JannahApp
import com.salt.apps.jannah.presentation.app.rememberAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var mainState: SharedState by mutableStateOf(SharedState())

        splashScreen.setKeepOnScreenCondition {
            mainViewModel.sharedState.value.isLoading
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.sharedState.onEach { mainState = it }.collect()
            }
        }

        enableEdgeToEdge()

        setContent {
            val darkTheme = shouldUseDarkTheme(mainState)
            JannahTheme(
                darkTheme = darkTheme
            ) {
                JannahApp(
                    appState = rememberAppState(sharedViewModel = mainViewModel),
                )
            }
        }
    }

    @Composable
    private fun shouldUseDarkTheme(sharedState: SharedState): Boolean {
        return when {
            sharedState.isLoading -> isSystemInDarkTheme()
            sharedState.setting != null -> {
                when (sharedState.setting?.themeConfig) {
                    FOLLOW_SYSTEM -> isSystemInDarkTheme()
                    LIGHT -> false
                    DARK -> true
                    null -> isSystemInDarkTheme()
                }
            }

            else -> isSystemInDarkTheme()
        }
    }
}

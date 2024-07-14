package com.salt.apps.jannah.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.salt.apps.core.design.theme.JannahTheme
import com.salt.apps.jannah.presentation.app.JannahApp
import com.salt.apps.jannah.presentation.app.rememberAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var mainState: MainActivityState by mutableStateOf(MainActivityState.Loading)

        splashScreen.setKeepOnScreenCondition {
            mainViewModel.mainActivityStateFlow.value is MainActivityState.Loading
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.mainActivityStateFlow.onEach { mainState = it }.collect()
            }
        }

        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState()
            JannahTheme {
                JannahApp(appState = appState)
            }
        }
    }
}

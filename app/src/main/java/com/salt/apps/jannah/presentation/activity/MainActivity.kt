package com.salt.apps.jannah.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salt.apps.core.design.theme.JannahTheme
import com.salt.apps.jannah.presentation.app.JannahApp
import com.salt.apps.jannah.presentation.app.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.isLoading.value
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

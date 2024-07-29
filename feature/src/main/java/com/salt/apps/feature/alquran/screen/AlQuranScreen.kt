package com.salt.apps.feature.alquran.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.salt.apps.core.viewmodel.SharedViewModel
import com.salt.apps.feature.alquran.screen.components.AlQuranContentScreen

@Composable
fun AlQuranScreen(sharedViewModel: SharedViewModel, onSurahClick: (Int) -> Unit) {
    val surahState by sharedViewModel.state.collectAsStateWithLifecycle()
    AlQuranContentScreen(surahState = surahState, onSurahClick = onSurahClick)
}
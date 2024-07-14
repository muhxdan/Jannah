package com.salt.apps.jannah.presentation.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salt.apps.core.domain.usecase.AlQuranUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val alQuranUseCase: AlQuranUseCase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val mainActivityStateFlow: StateFlow<MainActivityState> =
        alQuranUseCase.getQuran().flatMapConcat { surahList ->
            surahList.asFlow().flatMapMerge { surah ->
                alQuranUseCase.getDetailSurah(
                    surah.nomor,
                )
            }
        }.map {
            MainActivityState.Success
        }.catch {
            Log.e("ERROR", "$it")
        }.stateIn(
            scope = viewModelScope,
            initialValue = MainActivityState.Loading,
            started = SharingStarted.WhileSubscribed(10_000),
        )
}

sealed interface MainActivityState {
    data object Loading : MainActivityState
    data object Success : MainActivityState
}
package com.salt.apps.feature.alquran.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salt.apps.core.viewmodel.SharedState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlQuranContentScreen(
    surahState: SharedState,
    onSurahClick: (Int) -> Unit,
) {
    Column {
        TopAppBar(
            title = { Text("Al Quran", modifier = Modifier.padding(0.dp)) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            windowInsets = WindowInsets(top = 0),
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            when {
                surahState.detailSurahList != null -> {
                    val data = surahState.detailSurahList
                    if (data != null) {
                        items(data.size) { index ->
                            SurahItem(data[index]) {
                                onSurahClick(data[index].nomor)
                            }
                        }
                    }
                }
            }
        }
    }
}

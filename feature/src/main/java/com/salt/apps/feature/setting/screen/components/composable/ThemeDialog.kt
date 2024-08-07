package com.salt.apps.feature.setting.screen.components.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salt.apps.core.domain.model.ThemeConfig
import com.salt.apps.core.ui.design.components.CustomDialog
import com.salt.apps.core.ui.design.components.DialogText

@Composable
fun ThemeDialog(
    currentTheme: ThemeConfig,
    onDismissRequest: () -> Unit,
    onThemeSelected: (ThemeConfig) -> Unit
) {
    val themes = listOf(
        "System" to ThemeConfig.FOLLOW_SYSTEM,
        "Dark" to ThemeConfig.DARK,
        "Light" to ThemeConfig.LIGHT
    )

    var selectedTheme by remember { mutableStateOf(currentTheme) }

    CustomDialog(
        showDialog = true,
        title = "Select Theme",
        confirmText = "OK",
        dismissText = "Cancel",
        onConfirm = {
            onThemeSelected(selectedTheme)
            onDismissRequest()
        },
        onDismiss = onDismissRequest,
        text = DialogText.Content {
            Column {
                themes.forEach { (label, themeConfig) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedTheme == themeConfig,
                            onClick = { selectedTheme = themeConfig }
                        )
                        Text(
                            text = label,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    )
//    AlertDialog(
//        onDismissRequest = onDismissRequest,
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    onThemeSelected(selectedTheme)
//                    onDismissRequest()
//                }
//            ) {
//                Text("OK")
//            }
//        },
//        dismissButton = {
//            TextButton(onClick = onDismissRequest) {
//                Text("Cancel")
//            }
//        },
//        title = { Text("Select Theme") },
//        text = {
//            Column {
//                themes.forEach { (label, themeConfig) ->
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        RadioButton(
//                            selected = selectedTheme == themeConfig,
//                            onClick = { selectedTheme = themeConfig }
//                        )
//                        Text(
//                            text = label,
//                            modifier = Modifier.padding(start = 8.dp)
//                        )
//                    }
//                }
//            }
//        },
//        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
//    )
}

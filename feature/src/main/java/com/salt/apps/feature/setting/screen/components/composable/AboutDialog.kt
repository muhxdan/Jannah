package com.salt.apps.feature.setting.screen.components.composable

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.salt.apps.core.R
import com.salt.apps.core.ui.design.components.CustomDialog
import com.salt.apps.core.ui.design.components.DialogText.Content

@Composable
fun AboutDialog(onDismiss: () -> Unit) {
    val appVersion = "1.0.0"
    val context = LocalContext.current
    val features = context.resources.getStringArray(R.array.about_dialog_features)
    val learnMoreLink = "https://github.com/muhxdan/Jannah"

    CustomDialog(
        showDialog = true,
        onDismiss = onDismiss,
        onConfirm = onDismiss,
        title = "About Jannah",
        text = Content {
            LazyColumn {
                item {
                    Text(stringResource(id = R.string.about_dialog_description))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(id = R.string.about_dialog_features_title))
                }
                items(features) { feature ->
                    Text("• $feature")
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(id = R.string.about_dialog_version, appVersion))
                    Spacer(modifier = Modifier.height(3.dp))
                    Text("Learn more about Jannah at:")
                    Spacer(modifier = Modifier.height(5.dp))
                    ClickableText(text = AnnotatedString(learnMoreLink),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Blue
                        ),
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(learnMoreLink))
                            context.startActivity(intent)
                        })
                }
            }
        },
        confirmText = "OK",
        dismissText = ""
    )
}
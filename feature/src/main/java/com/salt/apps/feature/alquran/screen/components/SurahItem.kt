package com.salt.apps.feature.alquran.screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.salt.apps.core.design.theme.arabicFontFamily
import com.salt.apps.core.domain.model.DetailSurah

@Composable
fun SurahItem(dataQuran: DetailSurah, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().border(
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline),
            shape = RoundedCornerShape(10.dp)
        ).clip(shape = RoundedCornerShape(10.dp)).clickable { onClick() }
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                dataQuran.nomor.toString(), style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                dataQuran.namaLatin,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                "${dataQuran.arti} • ${dataQuran.jumlahAyat} Ayat",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            dataQuran.nama,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge.copy(fontFamily = arabicFontFamily)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}
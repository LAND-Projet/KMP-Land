package com.kmp.idea.android.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kmp.idea.SharedRes
import com.kmp.idea.android.ui.theme.textColor
import com.kmp.idea.android.util.AndroidStringResource

@Composable
fun CopyrightText() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = AndroidStringResource(id = SharedRes.strings.land_copyright_text),
            color = MaterialTheme.colorScheme.textColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

package com.kmp.idea.android.presentation.components.popup


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kmp.idea.SharedRes
import com.kmp.idea.android.presentation.common.ImageButton
import com.kmp.idea.android.ui.theme.popUpBackground
import com.kmp.idea.android.ui.theme.popUpContent
import com.kmp.idea.android.util.AndroidStringResource

@Composable
fun OptionDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.popUpBackground,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = AndroidStringResource(id = SharedRes.strings.select_picture_text_label),
                    color = MaterialTheme.colorScheme.popUpContent,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(30.dp))
                ImageButton(
                    eventClick = onButton1Click,
                    content = AndroidStringResource(
                        id = SharedRes.strings.take_picture_text_button
                    ),
                    iconContent = com.dardev.land.R.drawable.cameralight
                )
                Spacer(modifier = Modifier.height(20.dp))
                ImageButton(
                    eventClick = onButton2Click,
                    content = AndroidStringResource(
                        id = SharedRes.strings.take_image_from_storage_text_button
                    ),
                    iconContent = com.dardev.land.R.drawable.uploadicon
                )
            }
        }
    }
}

package com.kmp.idea.android.presentation.components.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kmp.idea.SharedRes
import com.kmp.idea.android.R
import com.kmp.idea.android.ui.theme.buttonFeedbackBackground
import com.kmp.idea.android.ui.theme.feedbackBoxBackground
import com.kmp.idea.android.ui.theme.feedbackTextForeground
import com.kmp.idea.android.ui.theme.lightBackground
import com.kmp.idea.android.util.AndroidStringResource

@Composable
fun FeedbackCard(onFeedbackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .background(
                color = MaterialTheme.colorScheme.feedbackBoxBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.feedbackTextForeground,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(
                    id = if (isSystemInDarkTheme()) {
                        com.dardev.land.R.drawable.pinwhite
                    } else {
                        com.dardev.land.R.drawable.pinlocation
                    }
                ),
                contentDescription = "Pin Icon",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = AndroidStringResource(id = SharedRes.strings.survey_feedback_title),
                color = MaterialTheme.colorScheme.feedbackTextForeground,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = AndroidStringResource(id = SharedRes.strings.survey_feedback_message),
                color = MaterialTheme.colorScheme.feedbackTextForeground,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onFeedbackClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.buttonFeedbackBackground
                )
            ) {
                Text(
                    text = AndroidStringResource(id = SharedRes.strings.survey_feedback_action),
                    color = lightBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

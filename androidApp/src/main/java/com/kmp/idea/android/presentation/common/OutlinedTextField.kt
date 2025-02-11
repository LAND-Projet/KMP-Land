package com.kmp.idea.android.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kmp.idea.android.R
import com.kmp.idea.android.ui.theme.errorColor
import com.kmp.idea.android.ui.theme.textFieldBackground
import com.kmp.idea.android.ui.theme.textFieldContent
import com.kmp.idea.android.ui.theme.warningColor
import com.kmp.idea.android.util.AndroidStringResource
import com.kmp.idea.domain.use_cases.ValidateResult
import dev.icerock.moko.resources.StringResource

@ExperimentalMaterial3Api
@Composable
fun OutlinedTextField(
    textValue: MutableState<String>,
    label: String,
    isPassword: Boolean = false,
    visibilityPassword: MutableState<Boolean> = mutableStateOf(false),
    iconPassword: Painter = painterResource(id = R.drawable.ic_home),
    eventChange: () -> ValidateResult,
) {
    var errorMessage = remember { mutableStateOf<StringResource?>(null) }
    Column {
        TextField(
            modifier =
                Modifier
                    .width(322.dp)
                    .height(75.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(50.dp),
                    )
                    .background(
                        color = MaterialTheme.colorScheme.textFieldBackground,
                        shape = RoundedCornerShape(50.dp),
                    ),
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                var data = eventChange()
                if (data.successful == false) {
                    errorMessage.value = data.errorMessage
                } else {
                    errorMessage.value = null
                }
            },
            placeholder = {
                Text(
                    label,
                    color = MaterialTheme.colorScheme.textFieldContent,
                )
            },
            textStyle = MaterialTheme.typography.labelSmall,
            isError = errorMessage.value != null,
            singleLine = true,
            colors =
                TextFieldDefaults.colors(
                    focusedLabelColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    focusedTextColor = warningColor,
                    unfocusedTextColor = warningColor,
                    errorTextColor = errorColor,
                    errorContainerColor = errorColor,
                    focusedPlaceholderColor = warningColor,
                    unfocusedPlaceholderColor = warningColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.DarkGray,
                ),
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = {
                        visibilityPassword.value = !visibilityPassword.value
                    }) {
                        Icon(
                            painter = iconPassword,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.textFieldContent,
                        )
                    }
                }
            },
            visualTransformation =
                if (isPassword) {
                    if (visibilityPassword.value) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                } else {
                    VisualTransformation.None
                },
        )
        if (errorMessage.value != null) {
            Text(
                text = AndroidStringResource(errorMessage.value!!),
                color = errorColor,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.width(300.dp),
            )
        }
    }
}

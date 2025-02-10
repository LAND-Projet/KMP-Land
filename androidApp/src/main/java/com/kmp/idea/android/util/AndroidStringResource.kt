package com.kmp.idea.android.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.kmp.idea.core.presentation.MokoStringsResources
import dev.icerock.moko.resources.StringResource

@Composable
fun AndroidStringResource(
    id: StringResource,
    vararg args: Any,
): String {
    return MokoStringsResources(LocalContext.current).get(id, args.toList())
}

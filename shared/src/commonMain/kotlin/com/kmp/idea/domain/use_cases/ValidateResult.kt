package com.kmp.idea.domain.use_cases

import dev.icerock.moko.resources.StringResource

data class ValidateResult(
    val successful: Boolean,
    val errorMessage: StringResource? = null,
    val errorString: String? = null
)

data class ValidateResultWrapper(var result: ValidateResult)
package com.kmp.idea.domain.use_cases.post

import com.kmp.idea.SharedRes
import com.kmp.idea.domain.use_cases.ValidateResult

class ValidateDescription {
    fun evoke(content: String): ValidateResult {
        if (content.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_description_error_blank
            )
        }
        val containsLettersAndDigits = content.any { it.isDigit() } || content.any { it.isLetter() }

        if (!containsLettersAndDigits) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_description_error_contains
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}
package com.kmp.idea.domain.use_cases.user

import com.kmp.idea.SharedRes
import com.kmp.idea.domain.use_cases.ValidateResult

class ValidatePassword {
    fun evoke(password: String): ValidateResult {
        if (password.length < 8) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_password_error_blank
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }

        if (!containsLettersAndDigits) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_password_error_contains
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}
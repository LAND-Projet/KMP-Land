package com.kmp.idea.domain.use_cases.user

import com.kmp.idea.SharedRes
import com.kmp.idea.domain.use_cases.ValidateResult

class ValidateRepeatedPassword {
    fun evoke(password: String, repeatedPassword: String): ValidateResult {
        if (password != repeatedPassword) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_repeated_password_error_equal
            )
        }

        if (repeatedPassword.length < 8) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_repeated_password_error_length
            )
        }
        val containsLettersAndDigits = repeatedPassword.any { it.isDigit() } && repeatedPassword.any { it.isLetter() }

        if (!containsLettersAndDigits) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_repeated_password_error_contains
            )
        }

        return ValidateResult(
            successful = true
        )
    }
}
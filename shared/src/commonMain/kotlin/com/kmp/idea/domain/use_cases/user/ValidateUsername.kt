package com.kmp.idea.domain.use_cases.user

import com.kmp.idea.SharedRes
import com.kmp.idea.domain.use_cases.ValidateResult

class ValidateUsername {
    fun evoke(username: String): ValidateResult {
        if (username.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_username_error_blank
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}
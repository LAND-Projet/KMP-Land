package com.kmp.idea.domain.use_cases.user

import com.kmp.idea.SharedRes
import com.kmp.idea.domain.use_cases.ValidateResult

class ValidateEmail {
    fun evoke(email: String): ValidateResult {
        if (email.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_email_error_blank
            )
        }
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        if (!EMAIL_REGEX.toRegex().matches(email)) {
            return ValidateResult(
                successful = false,
                errorMessage = SharedRes.strings.validate_email_error_regex_match
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}
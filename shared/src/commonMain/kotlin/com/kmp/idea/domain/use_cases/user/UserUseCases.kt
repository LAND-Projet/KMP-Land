package com.kmp.idea.domain.use_cases.user

import com.kmp.idea.domain.use_cases.ValidateResult

data class UserUseCases(
    val validateEmail: ValidateEmail,
    val validateUsername: ValidateUsername,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword,
    val validateResult: ValidateResult
)

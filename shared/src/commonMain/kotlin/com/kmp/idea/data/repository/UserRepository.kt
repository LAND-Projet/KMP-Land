package com.kmp.idea.data.repository

import com.kmp.idea.domain.use_cases.ValidateResult
import com.kmp.idea.domain.use_cases.user.UserUseCases

class UserRepository(
    private val userUseCases: UserUseCases
) {
    fun scanEmailField(value: String): ValidateResult {
        return userUseCases.validateEmail.evoke(value)
    }

    fun scanUsernameField(value: String): ValidateResult {
        return userUseCases.validateUsername.evoke(value)
    }

    fun scanPasswordField(value: String): ValidateResult {
        return userUseCases.validatePassword.evoke(value)
    }

    fun scanRepeatPasswordField(value: String, repeatValue: String): ValidateResult {
        return userUseCases.validateRepeatedPassword.evoke(value, repeatValue)
    }
}
package com.kmp.idea.domain.use_cases.post

import com.kmp.idea.domain.use_cases.ValidateResult

data class PostUseCases(
    val validateDescription: ValidateDescription,
    val validateResult: ValidateResult
)

package com.kmp.idea.data.repository

import com.kmp.idea.domain.use_cases.ValidateResult
import com.kmp.idea.domain.use_cases.post.PostUseCases

class PostRepository(
    private val postUseCases: PostUseCases
) {
    fun verifyDescription(value: String): ValidateResult {
        return postUseCases.validateDescription.evoke(value)
    }
}
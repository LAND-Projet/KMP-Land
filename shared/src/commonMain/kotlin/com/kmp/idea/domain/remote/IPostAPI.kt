package com.kmp.idea.domain.remote

import com.kmp.idea.domain.model.Post
import com.kmp.idea.domain.use_cases.ValidateResult

interface IPostAPI {
    suspend fun postNewPost(post: Post, jwtToken: String): ValidateResult?
}
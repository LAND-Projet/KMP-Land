package com.kmp.idea.data.service

import com.kmp.idea.domain.model.Post
import com.kmp.idea.domain.remote.IPostAPI
import com.kmp.idea.domain.use_cases.ValidateResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostAPIServiceHelper: KoinComponent {
    private val service: PostAPIService by inject()

    suspend fun createNewPost(post: Post, jwtToken: String): ValidateResult? {
        return service.createNewPost(post, jwtToken)
    }

}